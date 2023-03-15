<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
    <title>Scan Qr Title</title>
</head>
<body>

<div class="card">
    <div class="card-body">
        <div>
            <button id="scan-button" class="btn btn-light">
                <i class="fas fa-qrcode"></i> Scan QR Code
            </button>
        </div>
        <div style="display: flex; margin-top: 5px;">
            <div style="flex: 1">
                <video id="video"></video>
            </div>
            <div style="padding: 0 5px;">
                <table id="item-table">
                    <thead>
                        <tr>
                            <th>Item ID</th>
                            <th>Warehouse</th>
                            <th>Rack</th>
                            <th>Tray</th>
                            <th>Quantity</th>
                        </tr>
                    </thead>
                    <tbody></tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script src="https://unpkg.com/@zxing/library@0.18.4"></script>
<script src="/mylib/js/call-ajax.js"></script>

<script>
    // Lấy đối tượng video và button từ HTML
    const video = $('#video');
    const scanButton = $('#scan-button');

    // css
    $('#item-table thead').css('display', 'none');

    // Thiết lập đối tượng quét mã QR
    const qrCodeScanner = new ZXing.BrowserQRCodeReader();

    // Biến lưu trạng thái đang quét hay không
    let isScanning = false;

    // Hàm để bật/tắt webcam
    function toggleScan() {
        if (!isScanning) {
            // Mở webcam
            navigator.mediaDevices.getUserMedia({video: true})
                .then(function(stream) {
                    video[0].srcObject = stream;
                    video[0].play();
                    isScanning = true;
                    scanButton.text('Stop Scan');

                    // Quét mã QR
                    qrCodeScanner.decodeFromVideoDevice(null, 'video', function(result) {
                        if(result){
                            // Xử lý mã QR tại đây
                            var itemId = result.text;
                            console.log(itemId);
                            callDB('/api/items/scan-qr?itemId='+ itemId, 'GET', null, function (result) {

                                $('#item-table tbody').text('');

                                // Chọn đối tượng table
                                var table = $('#item-table');

                                var data = result.data;
                                if(result.status){
                                    $.each(data, function(index, item) {
                                        var row = $('<tr>');
                                        $('<td>').html(item.item.id).appendTo(row);
                                        $('<td>').html(item.location.warehouse).appendTo(row);
                                        $('<td>').html(item.location.rack).appendTo(row);
                                        $('<td>').html(item.location.tray).appendTo(row);
                                        $('<td>').html(item.quantity).appendTo(row);
                                        $('#item-table tbody').append(row);
                                    });

                                    // Hiển thị lại title
                                    $('#item-table thead').css('display', 'contents');
                                    // CSS lại các cột và dòng trong table
                                    table.find('th, td').css({'border': '1px solid #ddd', 'padding': '8px', 'text-align': 'left', 'background': 'black'});
                                    // CSS lại tiêu đề bảng
                                    table.find('th').css({'background-color': '#4CAF50', 'color': 'white'});
                                } else{
                                    $('#item-table thead').css('display', 'none');
                                    var row = $('<tr>');
                                    $('<td>').html(data).appendTo(row);
                                    $('#item-table tbody').append(row);
                                    table.find('th, td').css({'color': 'red', 'background': 'black'});
                                }
                            });
                        }
                    });
                })
                .catch(function(error) {
                    console.log(error);
                });
        } else {
            qrCodeScanner.reset();
            isScanning = false;
            scanButton.text('Scan QR Code');
            video[0].pause();
        }
    }

    // Khi người dùng nhấn vào nút "Scan QR Code"
    scanButton.on('click', function() {
        toggleScan();
    });
</script>
</body>
</html>