var books = [];
var cart = [];

// 載入所有書籍資訊
function loadBooks() {
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (this.readyState == XMLHttpRequest.DONE && this.status == 200) {
			books = JSON.parse(this.responseText);
			showBooks(books);
		}
	}
	request.open("POST", "BookServlet", true);
	request.setRequestHeader("content-type", "application/json");
	var obj = { "action": "getAll" };
	var json = JSON.stringify(obj);
	request.send(json);
}

// 顯示所有書籍資訊
function showBooks(books) {
	var text = "<table>";
	text += "<tr><th>封面圖</th><th>ISBN書號</th><th>書名</th><th>定價</th><th>作者</th><th></th></tr>";
	for (var book of books) {
		// 如果該書沒有封面圖就設定為預設圖示
		var image = book.imageBase64 == null ? "images/no_image.jpg" : book.imageBase64;
		text += "<tr>";
		text += `<td><img width="100" src="${image}" onclick="loadImage(this)"></td>
                 <td>${book.isbn}</td>
                 <td>${book.name}</td>
                 <td>${book.price}</td>
                 <td>${book.author}</td>
                 <td><button type='button' id='${book.isbn}' onclick='updateBook(this)'>修改</button> 
                     <button type='button' id='${book.isbn}' onclick='deleteBook(this)'>刪除</button></td>`;
		text += "</tr>";
	}
	text += "</table>";

	document.getElementById("booksView").innerHTML = text;
}

function searchBook() {
	var name = document.getElementById("name").value;
	var searchedBooks = books.filter(book => book.name.toUpperCase().includes(name.toUpperCase()));
	showBooks(searchedBooks);
}

// 先將資料暫存於sessionStorage，去到修改頁面再取出
function updateBook(button) {
	var isbn = button.id;
	sessionStorage.setItem("isbn", isbn);
	window.location.href = "update.html";
}

function deleteBook(button) {
	var isbn = button.id;
	var request = new XMLHttpRequest();
	request.onreadystatechange = function() {
		if (this.readyState == XMLHttpRequest.DONE && this.status == 200) {
			var count = JSON.parse(this.responseText);
			if (count == 0) {
				window.alert("刪除失敗");
			} else {
				// 遠端DB刪除成功，本機端對應資料也該刪除
				var index = books.findIndex(book => book.isbn == isbn);
				books.splice(index, 1);
				window.alert("刪除成功");
				showBooks(books);
			}
		}
	}
	request.open("POST", "BookServlet", true);
	request.setRequestHeader("content-type", "application/json");
	var obj = { "action": "deleteBook", "isbn": isbn };
	var json = JSON.stringify(obj);
	request.send(json);
}
