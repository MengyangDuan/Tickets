/**
 * Created by disinuo on 17/3/13.
 */
$(function () {
    $.ajax({
        url:'/user/getInfo',
        success:function (data) {
            $('#hostel_info_money').html(data.bankMoney);
        },
        error:function (data) {
            alert(JSON.stringify(data));
        }
    });
    $.ajax({
        url:'/data/hostel/getInfo',
        success:function (data) {
            $('#hostel_info_descrip').html(data.descrip);
            $('#hostel_info_id').html(data.id);
            $('#hostel_info_name').html(data.name);
            $('#hostel_info_city').html(data.province+" - "+data.city);
            $('#hostel_info_address').html(data.address);
            $('#hostel_info_phone').html(data.phone);
            $('#hostel_info_img').attr('src',data.img);
            hostelPermitted=data.permitted;
            if(!hostelPermitted){
                $('#openRequestForm').show();
            }else {
                $('#openRequestForm').hide();
            }
        }
    });

});
$('#openRequestForm').submit(function (e) {
   $.ajax({
       type:'POST',
       url:'/hostel/requestManager',
       success:function (data) {
           alert('成功发出申请');
       }
   });
    e.preventDefault(); // avoid to execute the actual submit of the form.

});

var hostelPermitted=null;