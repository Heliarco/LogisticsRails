//const fs = require('fs');

(function() {




	const fs = require('fs');
	const path = require('path');
	

	var readconfig = function() {
		var cfgPath = ModelMeta.save_path.split('.').slice(0, -1).join('.') + '_multiexport.json';
		let rawdata = fs.readFileSync(cfgPath);
		let cfg = JSON.parse(rawdata);
		return  {
			
			originalFile : ModelMeta.save_path,
			projectName : cfg.name,

			shapefile: path.join(path.dirname(ModelMeta.save_path),cfg.shapefile),
			shapeclass: path.parse(cfg.shapefile).name,
			shapenamespace: cfg.shapenamespace,

			combinations : cfg.combinations.map(mutDef => {
				return {
					suffix: mutDef.suffix,

					path: path.join(
						path.dirname(cfgPath),
						cfg.targetfolder,
						cfg.name+mutDef.suffix+".json"),
					
					transformations: mutDef.transform.match(/.{1,2}/g)
				}
			})
		};
	}

	var exportMaster = function() {
		var job = readconfig();
		
		let shapesnippets = [];

		
		let jobqueue = [];

		job.combinations.forEach(combination => {
			
			jobqueue.push(() => { applyTransformations(combination.transformations);});
			
			// Opens file menu
			jobqueue.push(() => { 
				$("#menu_bar > li:nth-child(1)").click();
			});

			jobqueue.push( ()=> {
				shapesnippets.push(getVoxelShapeSnippet(combination.suffix));
			});
			
			jobqueue.push(() => { 
				var s = $("[menu_item='export']")
				s.mouseover();
			});

			jobqueue.push(() => { executeExport(
				combination.path
			);});

			jobqueue.push(() => { rewindTransformations(combination.transformations);});
		});

		jobqueue.push(() => {
			writeShapes(shapesnippets,job);
		});


		var myInterval = setInterval(function () {
			try {
                let j = jobqueue.shift();
				j();
				if(jobqueue.length <= 0){

					clearInterval(myInterval);
				}
			}
			catch(e){clearInterval(myInterval); throw e;}
		}, 50);


	}
	var applyTransformations = function(transformations){
		if (!transformations) {
			return;
		}
		unselectAll();
		selectAll();
		transformations.forEach(transform => {
			switch(transform){
				case "x+":
					rotateSelected(0,1);
					break;
				case "x-":
					rotateSelected(0,3);
					break;
				case "y+":
					rotateSelected(1,1);
					break;
				case "y-":
					rotateSelected(1,3);
					break;
				
				case "z+":
					rotateSelected(2,1);
					break;
				case "z-":
					rotateSelected(2,3);
					break;
					
				
				default: 
					console.log("dunno: " + transform);
			}
		})
		unselectAll();
	}
	var rewindTransformations = function(transformations) {
		if (!transformations){
			return;
		}
		unselectAll();
		selectAll();
		transformations.reverse().forEach(transform => {
			switch(transform){
				case "x+":
					rotateSelected(0,3);
					break;
				case "x-":
					rotateSelected(0,1);
					break;
				case "y+":
					rotateSelected(1,3);
					break;
				case "y-":
					rotateSelected(1,1);
					break;
				
				case "z+":
					rotateSelected(2,3);
					break;
				case "z-":
					rotateSelected(2,1);
					break;


				default: 
					console.log("dunno: " + transform);
			}
		})
		unselectAll();
	}


	var executeExport = function(p) {
		//debugger;
		var oldD = ElecDialogs.showSaveDialog;
		ElecDialogs.showSaveDialog = (a,b,c) => {
		
		
			//debugger;
			c(p);
		
		
		};

		var oldName = ModelMeta.export_path;

		ModelMeta.export_path = p;
		var s = $("li [title='Export a Minecraft Java Edition block or item model']");
		if(s[0] === undefined) {
			debugger;
		}
		s.click() // Sighs this is the best hook i could find
		ModelMeta.export_path = oldName;
		ElecDialogs.showSaveDialog = oldD;


	}



	//Project.name =="rail_straight"


	//ModelMeta.save_path == Full file path


    var multiExportAction = new Action({
        id: 'multi_export',
        name: 'Export multiple',
        icon: 'flip_to_back',
        description: 'Exports multiple rotation permutations of the same model',
        category: 'file',
        condition: () => Format.id === Formats.java_block.id,
        click: function (event) {
            exportMaster();
        }
    });

	var autoexportMaster = function() {
		setTimeout(() => {
			//exportMaster();
			exportMaster();
			setTimeout(() => {
				ElecDialogs.showMessageBox = () => true;
				app.quit()
			}, 1000);
		}, 2000);
	}


    Plugin.register('vertexspace-model-rotate-exporter', {
        title: 'Model rotater and exporter',
        author: 'Heliarco',
        icon: 'icon-format_block',
        description: 'Allows for exporting many rotation permutations of a block with one click',
        version: '1.0.0',
        variant: 'desktop',
        onload() {
			MenuBar.addAction(multiExportAction, 'file.export' );
			if(process.env.BBAUTOEXPORT) {
				autoexportMaster();
			}
			console.log(process.env);
		},
		onunload() {
			multiExportAction.delete();
		}
		
	});
	





	//---- voxelshape

	function writeShapes(shapesnippets,cfg){
		
		var header = `package ${cfg.shapenamespace};
import net.minecraft.block.Block;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import java.util.stream.Stream;

public class ${cfg.shapeclass} {
	private ${cfg.shapeclass}(){}
`;
		
		var footer = "}";
		
		var text = header;
		
		for(var i = 0; i < shapesnippets.length; i++){
			
			text = text + shapesnippets[i] + "\n";
		}
		text = text + footer;

		fs.writeFileSync(cfg.shapefile,text);
	}

	function getVoxelShapeSnippet(suffix){
		
		var voxelShapeGroup = searchVoxelShapeGroup(Outliner.elements);
	
		if(voxelShapeGroup === undefined) return;
	
		var output = generateShape(voxelShapeGroup);
		return "\tpublic static final VoxelShape " + "S" + suffix.toUpperCase() + " = " + output;
	}


	function searchVoxelShapeGroup(elements){
		var rGroup;
		Group.all.forEach(
			group => {
				if(group.name === "VoxelShapes") {
				    rGroup = group;
				}
			}
		)
		return rGroup;
	}



	function generateShape(group){
		
		var method = [];
		
		for(var i = 0; i < group.children.length; i++){
			var child = group.children[i];
			if(child instanceof Group){
				method.push(generateShape(child));
			}else{
				method.push("\t\tBlock.makeCuboidShape" + "(" + child.from[0] + ", " + child.from[1] + ", " + child.from[2] + ", " + child.to[0] + ", " + child.to[1] + ", " + child.to[2] + ")");
			}
		}
		
		
		
		var output = "Stream.of(\n";
		
		for(i = 0; i < method.length; i++){
			if(i == method.length -1){
				output = output + method[i];
			}else{
				output = output + method[i] + ",\n";
			}
		}
		
		output = output + ").reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR)).get();";
		
		return output;
		
	}



})();


/*
new Action('rotate_x_cw', {
		name: tl('action.rotate_cw', 'X'),
		icon: 'rotate_right',
		color: 'x',
		category: 'transform',
		click: function () {
			rotateSelected(0, 1);
		}
	})
	new Action('rotate_x_ccw', {
		name: tl('action.rotate_ccw', 'X'),
		icon: 'rotate_left',
		color: 'x',
		category: 'transform',
		click: function () {
			rotateSelected(0, 3);
		}
	})
	new Action('rotate_y_cw', {
		name: tl('action.rotate_cw', 'Y'),
		icon: 'rotate_right',
		color: 'y',
		category: 'transform',
		click: function () {
			rotateSelected(1, 1);
		}
	})
	new Action('rotate_y_ccw', {
		name: tl('action.rotate_ccw', 'Y'),
		icon: 'rotate_left',
		color: 'y',
		category: 'transform',
		click: function () {
			rotateSelected(1, 3);
		}
	})
	new Action('rotate_z_cw', {
		name: tl('action.rotate_cw', 'Z'),
		icon: 'rotate_right',
		color: 'z',
		category: 'transform',
		click: function () {
			rotateSelected(2, 1);
		}
	})
	new Action('rotate_z_ccw', {
		name: tl('action.rotate_ccw', 'Z'),
		icon: 'rotate_left',
		color: 'z',
		category: 'transform',
		click: function () {
			rotateSelected(2, 3);
		}
	})

*/