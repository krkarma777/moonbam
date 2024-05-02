<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://fastly.jsdelivr.net/npm/hls.js@latest"></script>
    <title>문화인들의 밤</title>
    <style>
        video {
            width: 100%;
            height: auto;
        }
    </style>
</head>
<body>
<video id="video" controls playsinline autoplay muted></video>
<script>
    // DOMContentLoaded 이벤트가 발생하면 비디오 요소와 HLS 스트림을 설정
    document.addEventListener("DOMContentLoaded", function() {
        // 비디오 요소 선택
        var video = document.getElementById('video');

        // API 호출하여 m3u8 URL 가져오기
        fetch('https://cfpwwwapi.kbs.co.kr/api/v1/landing/live/channel_code/N91')
            .then(response => response.json())
            .then(data => {
                // m3u8 URL을 videoSrc 변수에 저장
                var videoSrc = data.channel_item[0].service_url;

                // HLS.js를 사용하여 m3u8 스트림을 처리
                if (Hls.isSupported()) {
                    var hls = new Hls();
                    hls.loadSource(videoSrc);
                    hls.attachMedia(video);
                    hls.on(Hls.Events.MANIFEST_PARSED, function() {
                        video.play();
                    });
                } else if (video.canPlayType('application/vnd.apple.mpegurl')) {
                    // iOS 기기에서 네이티브 지원 확인
                    video.src = videoSrc;
                    video.addEventListener('loadedmetadata', function() {
                        video.play();
                    });
                }
            })
            .catch(error => console.error('Error loading the video source:', error));
    });
</script>
</body>
</html>
