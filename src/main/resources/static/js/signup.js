window.onload = function() {
    // 페이지가 완전히 로드된 후 실행할 코드
    const submitBtn = document.getElementById('submit')
    submitBtn.disabled = true;
};

document.getElementById('form-button-id').addEventListener('click', function() {
    // Fetch 요청 보내기
    const div = document.getElementById('form-id-container');
    if(div.nextElementSibling && div.nextElementSibling.tagName.toLowerCase() === 'p') {
        div.nextElementSibling.remove();
    }
    const username = document.getElementById('username').value;
    const p = document.createElement('p')
    const submitBtn = document.getElementById('submit')
    fetch('/user/duplicate?username='+username, {method:"GET"}) // 요청할 URL
        .then(response => {
            if (!response.ok) {
                p.innerText = "이미 가입된 회원이 존재합니다."
                p.classList.add("duplicate-false")
                div.insertAdjacentElement('afterend', p);
                submitBtn.setAttribute("disabled", true)
            }
            else {
                p.innerText = "해당 이름은 사용 가능합니다."
                p.classList.add("duplicate-true")
                div.insertAdjacentElement('afterend', p);
                submitBtn.removeAttribute("disabled")
            }
        })
});