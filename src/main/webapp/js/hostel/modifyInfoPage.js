/**
 * Created by disinuo on 17/3/13.
 */
$(document).ready(function () {

    $.ajax({
        url:'/data/hostel/getInfo',
        success:function (data) {
            $('#hostelName').val(data.name);
            $('#address').val(data.address);
            $('#phone').val(data.phone);
            $('#descrip').val(data.descrip);

        }
    });

});


$('#modifyHostelInfoForm').submit(function (e) {
    e.preventDefault(); // avoid to execute the actual submit of the form.
    $.ajax({
        type:'POST',
        url:'/hostel/modifyInfo',
        data:{
            name:$('#hostelName').val(),
            address:$('#address').val(),
            phone:$('#phone').val(),
            descrip:$('#descrip').val()
        },
        success:function (data) {
            $('#msg').html(data);
        }
    })
})

