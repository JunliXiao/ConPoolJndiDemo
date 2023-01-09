var books = [];
var cart = [];

// 載入所有書籍資料、購物車內容並顯示購物車資訊
function loadData() {
	loadBooks();
	loadCart();
	showCart();
}

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
                 <td><button type='button' id='${book.isbn}' class='add' onclick='changeItem(this)'>加入購物車</button></td>`;
		text += "</tr>";
	}
	text += "</table>";

	document.getElementById("booksView").innerHTML = text;
}

function search() {
	var name = document.getElementById("name").value;
	var searchedBooks = books.filter(book => book.name.toUpperCase().includes(name.toUpperCase()));
	showBooks(searchedBooks);
}

// 載入購物車資訊
function loadCart() {
	var jsonArray = JSON.parse(localStorage.getItem("cart"));
	if (jsonArray == null) {
		return;
	}
	for (obj of jsonArray) {
		var item = new CartItem(obj.book, obj.qty);
		cart.push(item);
	}
}

// 顯示購物車內容
function showCart() {
	var text = "<table>";
	text += "<tr><th>封面</th><th>ISBN書號</th><th>書名</th><th>定價</th><th>作者</th><th>數量</th><th></th></tr>";
	for (item of cart) {
		var book = item.book;
		var image = book.imageBase64 == null ? "images/no_image.jpg" : book.imageBase64;
		text += "<tr>";
		// 當使用者增減數量時，只更改數量顯示而不希望重刷，所以將<p>的id設為'qty_${book.isbn}'
		text += `<td><img width="100" src="${image}"</td>
                         <td>${book.isbn}</td>
                         <td>${book.name}</td>
                         <td>${book.price}</td>
                         <td>${book.author}</td>
                         <td>
							<p id='qty_${book.isbn}'>${item.qty}</p>
							<div>
								<button type='button' id='${book.isbn}' class='add' onclick='changeItem(this)'>+</button>
								<button type='button' id='${book.isbn}' class='minus' onclick='changeItem(this)'>-</button>
							</div>
						 </td>
                         <td><button type='button' id='${book.isbn}' class='remove' onclick='changeItem(this)'>移除</button></td>`;
		text += "</tr>";

	}
	text += "</table>";
	document.getElementById("cartView").innerHTML = text;
}

// 點擊「加入購物車」、「+」、「-」、「移除」按鈕都會呼叫此方法
function changeItem(button) {
	var isbn = button.id;
	var className = button.className;
	// 利用ISBN找到儲存在本機記憶體內對應的書
	var book = books.find(b => b.isbn == isbn);
	var index = cart.findIndex(cartItem => cartItem.book.isbn == isbn);
	switch (className) {
		// 「加入購物車」、「+」按鈕的類別名都是"add"
		case "add":
			// 檢查購物車內是否已經有此書，如果有就將數量+1，沒有就直接新增此項目(數量指定為1)
			if (index != -1) {
				var qty = ++cart[index].qty;
				// 只將數量改變，不用重刷畫面
				document.getElementById(`qty_${isbn}`).innerHTML = qty;
			} else {
				// 新增到購物車內的為CartItem，只儲存isbn與qty(購買量)
				cart.push(new CartItem(book, 1));
				showCart();
			}
			break;
		case "minus":
			// 當數量為1時，使用者點擊「-」按鈕要提示是否刪除
			if (cart[index].qty <= 1) {
				if (confirm("要移除此書嗎？")) {
					// 將指定index的1個項目移除
					cart.splice(index, 1);
					showCart();
				}
				return;
			}
			// 只將數量改變，不用重刷畫面
			document.getElementById(`qty_${isbn}`).innerHTML = --cart[index].qty;
			break;
		case "remove":
			// 將指定index的1個項目移除
			cart.splice(index, 1);
			showCart();
			break;
		default:
			break;
	}
	// 將購物車轉成JSON後儲存至local storage
	localStorage.setItem("cart", JSON.stringify(cart));
}
