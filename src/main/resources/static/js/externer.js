/**
 * 공통자바 스크립트
 */

var replyManager = (function() {
	var getAll = function(obj, callback) {
		console.log("getAll....." + obj);
		//getJSON는 ajax의 하나의 방법
		$.getJSON("/replies/board/" + obj, callback)
	};


	var add = function(obj, callback) {
		console.log("add reply.....");
		$.ajax({
			url: "/replies/" + obj["bno"],
			data: JSON.stringify(obj),
			dataType: "json",
			type: "post",
			contentType: "application/json",
			success: callback
		});
	};


	var update = function(obj, callback) {
		console.log("update reply.....");
			$.ajax({
			url: "/replies/" + obj["bno"],
			data: JSON.stringify(obj),
			dataType: "json",
			type: "put",
			contentType: "application/json",
			success: callback
		});
	};


	var remove = function(obj, callback) {
		console.log("remove reply.........");
		$.ajax({
			url: "/replies/" + obj["bno"] + "/" + obj["rno"],
			type: "delete",
			success: callback

		});
	};



	return {
		"getAll": getAll,
		"add": add,
		"update": update,
		"remove": remove
	};


})();