
$(document).ready(function () {
    var args=requestParamFormatter();
    activityId=args['activityId'];
    initActivity();
});

function initActivity(){
    $.ajax({
        type: 'GET',
        data:{activityId:activityId},
        url:'/data/user/getActivityInfo',
        success:function (data) {
            $('#name').html(data.name);
            $('#address').html(data.place);
            $('#time').html(data.time);
            $('#description').html(data.description);
            // $("#selectSeat").attr("href","/user/selectSeat?activityId="+activityId);
        }
    })
    $.ajax({
        type: 'GET',
        data:{activityId:activityId},
        url:'/data/user/getTicketPrice',
        success:function (data) {
            for(var i=0;i<data.length;i++) {
                $('.seatType').append("<button class='addSeat' id='"+data[i]+"' isSelected=0 style='margin-right: 15px' onclick='addEvent("+data[i]+")'>"+data[i]+"</button>");
            }
        }
    })
}
function addEvent(id) {
    $('#num').show();
    var selectElement=document.getElementById(id);
    var price=id;
    var selected=parseInt(selectElement.getAttribute("isSelected"));
    if(selected==0) {
        var elementId='a'+id;
        //var addContent = "<div class='selectedSeats'><span>" + price + ":</span><input type='number' placeholder='1'><button class='delSeat'id='"+elementId+"' onclick='"+delEvent(elementId)+"'>delete</button></div>";
        $('.seatNum').append("<div class='selectedSeats'><span>" + price + ":</span><input type='number' value='1'><button value='"+id+"' class='delSeat'id='"+elementId+"' onclick='delEvent(this)'>delete</button></div>");
    }
    selectElement.setAttribute('isSelected',1);
}


function delEvent(btu) {
   $(btu.parentNode).remove();
   var elementId=btu.value;
   document.getElementById(elementId).setAttribute('isSelected',0);

}



$('#directOrder').on('click', function () {
    calTotal();
    if(counter<=20){
        window.location.href="/user/book?activityId="+activityId+"&seatNo="+seatList.toString()+"&seatPrice="+totalPrice
    }
    else {
        alert('总数不能超过20！');
        seatList=[];
        counter=0;
        totalPrice=0;
    }
});
$('#selectSeat').on('click', function () {
    location.replace("/user/selectSeat?activityId="+activityId);
});

function calTotal() {
    var selected=$('.selectedSeats');
    for(var i=0;i<selected.length;i++){
        var price=parseFloat($(selected[i]).find("span").html());
        var number=parseInt($(selected[i]).find("input").val());
        counter=counter+number;
        totalPrice=totalPrice+price*number;
        seatList.push(counter+"_"+price);
    }
}
var activityId=null;
var seatList=[];
var counter=0;
var totalPrice=0;