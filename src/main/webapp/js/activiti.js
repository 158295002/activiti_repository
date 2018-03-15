function doUpload(){
    var value = document.getElementById("fileU").value;
    if (value == null || value == ""){
        alert('还没上传文件，请重新上传!');
    }
}

function formateDate(date) {

    return date.format("yyyy-MM-dd hh:mm:ss");
}