const menu = document.querySelector(".card__menu--icon-tablet")
const menuNavbar = document.querySelector(".card__menu--list-tablet")
const user = document.querySelector(".card__user--icon")
const userNavbar = document.querySelector(".card__user--list")


const category_icon = document.querySelector(".category")
const category_menu = document.querySelector(".menu__category")

menu.addEventListener("click", (e) => {
    menuNavbar.classList.toggle("hide")
})

user.addEventListener("click", (e) => {
    userNavbar.classList.toggle("hide")
})

category_icon.addEventListener("click", (e) => {
    category_menu.classList.toggle("hide")
})

