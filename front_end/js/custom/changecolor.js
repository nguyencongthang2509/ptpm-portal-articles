var colorImage = {};
colorImage.changeColorImage = function () {
  var colorThief = new ColorThief();

// Lấy đối tượng hình ảnh
var myImage = document.getElementById("myImage");

// Lấy màu chủ đạo từ hình ảnh
colorThief.getColor(myImage, function (color) {
  // Đặt màu nền cho phần tử cha của hình ảnh
  var parentElement = myImage.parentElement;
  parentElement.style.backgroundColor = "rgb(" + color.join(", ") + ")";
});
};
