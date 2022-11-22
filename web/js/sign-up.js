

//re-pass
const toggleRePassword = document.querySelector("#toggleRePassword")
const re_password = document.querySelector("#re-password") 

toggleRePassword.addEventListener("click", (e) => {
    const passwordType = re_password.getAttribute("type") === "password"
                        ? "text" : "password"
    re_password.setAttribute("type", passwordType)

    const className =  e.target.className.includes("slash")
        ? "fa-solid fa-eye" : "fa-solid fa-eye-slash"
    e.target.className = className
})

//re-pass
const toggleOldPassword = document.querySelector("#toggleOldPassword")
const old_password = document.querySelector("#old-password") 

toggleOldPassword.addEventListener("click", (e) => {
    const passwordType = old_password.getAttribute("type") === "password"
                        ? "text" : "password"
    old_password.setAttribute("type", passwordType)

    const className =  e.target.className.includes("slash")
        ? "fa-solid fa-eye" : "fa-solid fa-eye-slash"
    e.target.className = className
})
