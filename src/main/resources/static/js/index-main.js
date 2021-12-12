// 主页面JavaScript

// 判断用户是否输入了必要参数
function ChickInput() {
    var name = document.getElementById("name").value;
    var file = document.getElementById("input-file-upload").value;
    if ("" == name) {
        alert("请输入上传人");
        return false;
    }
    if ("" == file || 0 == file.length) {
        alert("请选择要上传的文件");
        return false;
    }

    return true;
}