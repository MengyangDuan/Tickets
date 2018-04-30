/**
 * Created by disinuo on 17/3/13.
 */
var TABLE_HEIGHT=600;
function imgFormatter(value,row,index) {
    return [
        '<img ',
        'src="',
        value,
        '" alt="图片" class="image-little"',
        '/>'
    ].join('');
}
function imgWithLinkFormatter_vip(value,row,index) {
    return [
        '<a',
        ' href="/vip/rooms?hostelId=',
        row.hostelId,
        '"><img ',
        'src="',
        value,
        '" alt="图片" class="image-little"',
        '/></a>'
    ].join('');
}
function moneyFormatter(value) {
    return '￥'+value;
}

function roomFormatter(value,row,index) {
        return [
            row.roomName,
            "  ￥",
            row.roomPrice
        ].join('');
}
function guestWithIDCardFormatter(value,row,index) {
    var guests=row.guestVOS;
    if(guests.length==1){
        var guest=guests[0];
        return [
            guest.userRealName,
            guest.idCard,
            vipIdToLabelHelper(guest.vipId)

        ].join('  ');
    }else {
        var res="";
        guests.forEach(function (guest) {
            res+= [
                guest.userRealName,
                guest.idCard,
                vipIdToLabelHelper(guest.vipId),
                '<br>'
            ].join('  ');
        });
        return res;
    }
}
function guestFormatter(value,row,index) {
    var guests=row.guestVOS;
    if(guests.length==1){
        var guest=guests[0];
        return [
            guest.userRealName,
            vipIdToLabelHelper(guest.vipId)

        ].join('  ');
    }else {
        var res="";
        guests.forEach(function (guest) {
            res+= [
                guest.userRealName,
                vipIdToLabelHelper(guest.vipId),
                '<br>'
            ].join('  ');
        });
        return res;
    }
}
function vipIdToLabelHelper(vipId) {
    var viplabel ='<span class="label label-primary">会员</span>';
    var unVIPlabel ='<span class="label label-default">非会员</span>';
    if(vipId>0){
        return viplabel;
    }else return unVIPlabel;
}
function requestParamFormatter() {
    var qs=(location.search.length>0?location.search.substring(1):"");
    var args={};
    var items = qs.split('&');
    var item=null,key=null,value=null;

    for(var i=0,len=items.length;i<len;i++){
        item=items[i].split('=');
        key=decodeURIComponent(item[0]);
        value=decodeURIComponent(item[1]);
        args[key]=value;
    }
    return args;
}



function dateAdder(date,n) {
    // var uom = new Date(new Date()-0+n*86400000);
    // uom = uom.getFullYear() + "-" + (uom.getMonth()+1) + "-" + uom.getDate();
    // return uom;

    return new Date(date-0+n*86400000);
}
$('.msg').click(function () {
    this.style.display='none';
});

Array.prototype.indexOf = function(val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == val) return i;
    }
    return -1;
};

Array.prototype.remove = function(val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
};

Date.prototype.format = function(format) {
    var o = {
        "M+" : this.getMonth() + 1,// month
        "d+" : this.getDate(),// day
        "h+" : this.getHours(),// hour
        "m+" : this.getMinutes(),// minute
        "s+" : this.getSeconds(),// second
        "q+" : Math.floor((this.getMonth() + 3) / 3),// quarter
        "S" : this.getMilliseconds()
        // millisecond
    };
    if (/(y+)/.test(format) || /(Y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for ( var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }
    return format;
};

