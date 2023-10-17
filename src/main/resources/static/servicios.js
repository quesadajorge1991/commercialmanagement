function addGroup() {
	var grupo = $("#grupo").val();
	var url = '/addGrupo';

	if (true) {
		$.ajax({
			url: url,
			dataType: 'json',
			method: 'POST',
			data: {
				grupo: grupo
			},
			success: function(data) {
				$("#grupo").val("");
				//$('#loadMunicipiosFragment').addClass('chosen-select').trigger('change');
				// $("#loadTableFragment").empty();
				$("#loadGrupoFragment").load('/getGrupo');
				toastr[data.msgtipo](data.msgbody, data.msgtitu);
			},
			error: function(data) {
				toastr[data.msgtipo](data.msgbody, data.msgtitu);

			}

		});
	} else {

	}

}





function addNroContrato() {
	var nrocontrato = $("#nrocontrato").val();
	var url =/*[[@{/addNroContrato}]]*/ 'addNroContrato';
	if (true) {
		$.ajax({
			url: url,
			dataType: 'json',
			method: 'POST',
			data: {
				nrocontrato: nrocontrato
			},
			success: function(data) {

				//$('#loadMunicipiosFragment').addClass('chosen-select').trigger('change');
				// $("#loadTableFragment").empty();
				$("#loadGrupoFragment").load('/getGrupo');
				toastr[data.msgtipo](data.msgbody, data.msgtitu);


			},
			error: function(data) {
				toastr[data.msgtipo](data.msgbody, data.msgtitu);
			}

		});
	} else {



	}



}