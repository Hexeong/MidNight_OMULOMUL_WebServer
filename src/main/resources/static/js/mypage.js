document.addEventListener('DOMContentLoaded', function() {
    const currentPath = window.location.pathname; // 현재 경로 가져오기
    const specificPath1 = '/user/mypage'; // 비교할 특정 경로1
    const specificPath2 = '/user/login'; // 비교할 특정 경로2
    const specificPath3 = '/user/signup'; // 비교할 특정 경로3

    if (currentPath === specificPath1 || currentPath === specificPath2 || currentPath === specificPath3) {
        document.getElementById('tutorial').style.display = 'none'; // a 태그 숨기기
    }

    // 특정 클래스를 가진 모든 버튼 선택
    const buttons = document.querySelectorAll('.certificateBtn');

    // 각 버튼에 onclick 이벤트 추가
    buttons.forEach(button => {
        button.onclick = function(e) {
            // 클릭 시 실행할 로직
            const btn = e.target;
            const resultNo = btn.getAttribute("index");

            let popOption = "width=1064px, height=736px"
            window.open("/result/" + resultNo.toString(), '수료증', popOption);
        };
    });
});

