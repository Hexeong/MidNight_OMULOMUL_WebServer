document.getElementById('tutorial').addEventListener('click', function(event) {
    event.preventDefault(); // 기본 링크 동작 방지

    const targetPosition = document.querySelector('.tutorial-container').offsetTop; // 목표 위치
    window.scrollTo({
        top: targetPosition,
        behavior: 'smooth' // 부드러운 스크롤
    });
});