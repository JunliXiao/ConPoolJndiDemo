var imageBase64;

function openFile(event) {
    var pickImageView = document.getElementById("pickImageView");
    // target是最初觸發事件的DOM物件
    var files = event.target.files;
    if (files.length == 0) {
        pickImageView.src = "";
        return;
    }
    var reader = new FileReader();
    reader.onload = function () {
        // 讀進的圖檔資料是Base64格式
        imageBase64 = reader.result;
        pickImageView.src = imageBase64;
    };

    // 因為當初設定一次只能選一個檔案，所以只取第一個檔案
    reader.readAsDataURL(files[0]);
}

function insertBook() {
    var isbn = document.getElementById("isbn").value;
    var name = document.getElementById("name").value;
    var price = document.getElementById("price").value;
    var author = document.getElementById("author").value;
    var request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        if (this.readyState == XMLHttpRequest.DONE && this.status == 200) {
            var count = parseInt(this.responseText);
            if (count == 0) {
                window.alert("新增失敗")
            } else {
                window.alert("新增成功")
                window.location.href = "list.html";
            }
        }
    }
    request.open("POST", "BookServlet", true);
    request.setRequestHeader("content-type", "application/json");
    var obj = {
        "action": "insertBook",
        "book": { "isbn": isbn, "name": name, "price": price, "author": author, "imageBase64": imageBase64 }
    };
    var json = JSON.stringify(obj);
    request.send(json);
}

function clearInput() {
    imageBase64 = null;
    document.getElementById("pickImageView").src = ""; 
    document.getElementById("isbn").value = "";
    document.getElementById("name").value = "";
    document.getElementById("price").value = "";
    document.getElementById("author").value = "";
}
