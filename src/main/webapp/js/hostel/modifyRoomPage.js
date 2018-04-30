/**
 * Created by disinuo on 17/3/13.
 */
$(document).ready(function () {
   init();

});

var id;
var bookedNum;
var descrip;
function init() {
    id=requestParamFormatter()['roomId'];
    $("#startDate").flatpickr();
    $("#endDate").flatpickr();
    $.ajax({
        url:'/data/hostel/getRoom',
        data:{roomId:id},
        success:function (data) {
            console.log(data);
            $('#roomId').val(data.id);
            bookedNum=data.bookedNum;
            descrip=data.descrip;
            $('#name').val(data.name);
            $('#price').val(data.price);
            $('#capacity').val(data.capacity);
            $('#totalNum').val(data.totalNum);
            $("#startDate").val(data.startDate);
            $("#endDate").val(data.endDate);
            $('#descrip').val(data.descrip);
            // $('#img').val(data.img);
        }
    })
}
$('#modifyRoomForm').submit(function (e) {
    e.preventDefault(); // avoid to execute the actual submit of the form.
    var totalNum=$('#totalNum').val();
    var imgSrc="";
    console.log(totalNum);
    console.log(bookedNum);

    var formData=new FormData($('#img-form')[0]);
    console.log($('#img-form')[0]);
    console.log(formData);
    if(totalNum>=bookedNum){
        var data={
            name:$('#name').val(),
            price:$('#price').val(),
            roomId:id,
            capacity:$('#capacity').val(),
            totalNum:totalNum,
            descrip:descrip,
            startDate:$("#startDate").val(),
            endDate:$("#endDate").val()
        };
        $.ajax({
            type:'POST',
            url:'/hostel/modifyRoom',
            data: data,
            success:function (data) {
                $('#msg').html(data);
            }
        });
    }else {
        $('#msg').html('设置总房间数小于已预订数量啦');

    }

});

$(document).ready(function() {
    $(document).on("click", "#add-pic-btn", function () {

        var content = $("#content").val();

        $.ajax({
            url: '/hostel/uploadImg',
            type: 'POST',
            cache: false,
            data: new FormData($('#add-pic-form')[0]),
            processData: false,
            contentType: false,
            success: function (msg) {
                console.log(msg);

            },
            error: function (data) {
                console.log(data);
            }
        });

    });
});
var name=null;
var price=null;
var img=null;
var id=null;