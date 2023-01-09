function goPage(pageName) {
    window.location.href = pageName;
}

function back() {
    window.history.back();
}

function loadImage(img) {
    // 點圖後在新頁籤開啟原圖
    var newWindow = window.open();
    newWindow.document.body.innerHTML = `<img src="${img.src}">`;
}
