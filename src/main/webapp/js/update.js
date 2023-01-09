var imageBase64;
/* 進入修改頁面，要先將暫存在sessionStorage內的isbn書號取出，
 * 送至server查詢後取得該本書完整資訊並顯示在修改頁面上 */
function loadBook() {
	var isbn = sessionStorage.getItem("isbn");
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (this.readyState == XMLHttpRequest.DONE && this.status == 200) {
			var obj = JSON.parse(this.responseText);
			// 如果該書沒有封面圖就設定為預設圖示
			imageBase64 = obj.imageBase64 == null ? "images/no_image.jpg" : obj.imageBase64;
			document.getElementById("pickImageView").src = imageBase64;
			document.getElementById("isbn").value = isbn;
			document.getElementById("name").value = obj.name;
			document.getElementById("price").value = obj.price;
			document.getElementById("author").value = obj.author;
		}
	}
	request.open("POST", "BookServlet", true);
	request.setRequestHeader("content-type", "application/json");
	var obj = { "action": "findById", "isbn": isbn };
	var json = JSON.stringify(obj);
	request.send(json);
}

function openFile(event) {
	var pickImageView = document.getElementById("pickImageView");
	// target是最初觸發事件的DOM物件
	var files = event.target.files;
	if (files.length == 0) {
		pickImageView.src = "";
		return;
	}
	var reader = new FileReader();
	reader.onload = function() {
		// 讀進的圖檔資料是Base64格式
		imageBase64 = reader.result;
		pickImageView.src = imageBase64;
	};

	// 因為當初設定一次只能選一個檔案，所以只取第一個檔案
	reader.readAsDataURL(files[0]);
}

function updateBook() {
	var isbn = document.getElementById("isbn").value;
	var name = document.getElementById("name").value;
	var price = document.getElementById("price").value;
	var author = document.getElementById("author").value;
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (this.readyState == XMLHttpRequest.DONE && this.status == 200) {
			var count = parseInt(this.responseText);
			if (count == 0) {
				window.alert("修改失敗")
			} else {
				window.alert("修改成功")
				window.location.href = "list.html";
			}
		}
	}
	request.open("POST", "BookServlet", true);
	request.setRequestHeader("content-type", "application/json");
	var obj = {
		"action": "updateBook",
		"book": { "isbn": isbn, "name": name, "price": price, "author": author, "imageBase64": imageBase64 }
	};
	var json = JSON.stringify(obj);
	request.send(json);
}
