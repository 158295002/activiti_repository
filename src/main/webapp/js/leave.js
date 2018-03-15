function funcount() {
    var date1 = document.getElementById("start").value;
    var dt1 = countDate(date1);

    var date2 = document.getElementById("end").value;
    var dt2 = countDate(date2);

    var date3 = dt2.getTime() - dt1.getTime(); //时间差的毫秒数

    //计算出相差天数
    var days = Math.floor(date3 / (24 * 3600 * 1000))+1;
    document.getElementById("leaveDays").value = days;
}

function countDate(date) {
    var array = date.split("-");
    var dt = new Date(array[0], array[1], array[2]);
    return dt;
}


