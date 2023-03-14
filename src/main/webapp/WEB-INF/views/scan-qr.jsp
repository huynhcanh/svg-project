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
        <div class="text-center">
            <video id="video" height="450"></video>
        </div>
    </div>
</div>
<script src="https://unpkg.com/@zxing/library@0.18.4"></script>
<script>
    // Lấy đối tượng video và button từ HTML
    const video = $('#video');
    const scanButton = $('#scan-button');

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
                            console.log(result.text);
                        }
                        // Xử lý mã QR tại đây
                    });
                })
                .catch(function(error) {
                    console.log(error);
                });
        } else {
            console.log(123);
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