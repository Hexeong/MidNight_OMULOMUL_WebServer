document.getElementById('tutorial').addEventListener('click', function(event) {
    event.preventDefault(); // 기본 링크 동작 방지

    const targetPosition = document.querySelector('.tutorial-container').offsetTop; // 목표 위치
    window.scrollTo({
        top: targetPosition,
        behavior: 'smooth' // 부드러운 스크롤
    });
});

document.getElementById('download-button').addEventListener('click', function(event) {
    fetch('file/download', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.blob(); // Blob 형태로 응답을 받음
        })
        .then(blob => {
            // Blob을 URL로 변환
            const url = window.URL.createObjectURL(blob);
            const a = document.createElement('a'); // 링크 요소 생성
            a.href = url;
            a.download = 'testFile.pdf'; // 파일 이름 설정
            document.body.appendChild(a); // 링크 요소를 DOM에 추가
            a.click(); // 링크 클릭하여 다운로드 시작
            a.remove(); // 링크 요소 제거
            window.URL.revokeObjectURL(url); // URL 객체 해제
        })
        .catch(error => {
            console.error('Download error:', error);
        });
});