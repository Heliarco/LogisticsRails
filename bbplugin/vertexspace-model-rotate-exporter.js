//const fs = require('fs');

(function() {




	const fs = require('fs');
	const path = require('path');
	var plugin_variable_1;
	

	var readconfig = function() {
		var cfgPath = ModelMeta.save_path.split('.').slice(0, -1).join('.') + '_multiexport.json';
		let rawdata = fs.readFileSync(cfgPath);
		let cfg = JSON.parse(rawdata);
		return  {
			
			originalFile : ModelMeta.save_path,
			projectName : cfg.name,
			
			combinations : cfg.combinations.map(mutDef => {
				return {

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
		
		console.log(job);

		jobqueue = [];

		job.combinations.forEach(combination => {
			
			jobqueue.push(() => { applyTransformations(combination.transformations);});
			
			// Opens file menu
			jobqueue.push(() => { 
				$("#menu_bar > li:nth-child(1)").click();
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

		var myInterval = setInterval(function () {
			job = jobqueue.shift();
			job();
			if(jobqueue.length == 0){

				clearInterval(myInterval);
			}
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
		console.log(s[0]);
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



    Plugin.register('vertexspace-model-rotate-exporter', {
        title: 'Model rotater and exporter',
        author: 'Heliarco',
        icon: 'icon-format_block',
        description: 'Allows for exporting many rotation permutations of a block with one click',
        version: '1.0.0',
        variant: 'desktop',
        onload() {
			MenuBar.addAction(multiExportAction, 'file.export' );
		},
		onunload() {
			multiExportAction.delete();
		}
		
    });
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