const avatarInput = document.querySelector("#avatar-choose");
const avatarImg = document.querySelector("#avatar");

avatarInput.addEventListener('change', (e) => {
    var reader = new FileReader();
    reader.onload = function () {
        avatarImg.src = reader.result;
        document.querySelector("#avatar-base64").value = reader.result;
    }
    reader.readAsDataURL(e.target.files[0]);
});



