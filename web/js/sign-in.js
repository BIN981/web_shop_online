const user_input = document.querySelectorAll(".card__input");
const togglePassword = document.querySelector("#togglePassword")
const password = document.querySelector("#password")

const remember = document.querySelector("#remember")
const usernameInput = document.querySelector("#username")

const button = document.querySelector(".card__button")

user_input.forEach((element) => {
  element.addEventListener("focusin", (e) => {
    e.target.classList.add("focus");
  });
});

user_input.forEach((element) => {
  element.addEventListener("focusout", (e) => {
    e.target.classList.remove("focus");
  });
});

togglePassword.addEventListener("click", (e) => {
    const passwordType = password.getAttribute("type") === "password"
                        ? "text" : "password"
    password.setAttribute("type", passwordType)

    const className =  e.target.className.includes("slash")
        ? "fa-solid fa-eye" : "fa-solid fa-eye-slash"
    e.target.className = className
})

//check remember username
window.addEventListener("load", e => {
    var username = localStorage.getItem("username")
    if(username){
        usernameInput.value = username
    }
})

//remember username
button.addEventListener("click", e => {
    if(remember.checked && usernameInput.value.trim().length !== 0){
        localStorage.setItem("username", usernameInput.value)
    }
})

