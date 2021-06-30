const formElement = document.querySelector("#loginForm")
formElement.setAttribute('novalidate', 'true')
let errors = []
formElement.addEventListener('submit', function (event) {
    if (formElement.checkValidity()) {
        console.log("Success")
        formElement.submit()
    } else {
        event.preventDefault()
    }
})


const email = document.getElementsByName("fieldEmail")[0]
const psswd = document.getElementsByName("fieldPassword")[0]

const entries = formElement.elements
console.log(entries.length)
for (let i = 0; i < entries.length; i++) {
    let validInput = entries[i].nodeName.match('INPUT|TEXTAREA|SELECT')
    if (entries[i].willValidate && validInput) {
        console.log(entries[i])
        entries[i].addEventListener('change', reportError)
        entries[i].addEventListener('focus', reset)
    }
}

function isEmail(param) {
    return /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(param)
}

function isPsswd(param) {
    return /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&._-])[A-Za-z\d@$!%*?&_-]{8,16}$/.test(param)
}

function reportError(event) {
    const elem = document.getElementById("alert-box")
    const emailMsg = "Email not valid"
    const psswdMsg = "Password non valida"

    if (!isEmail(email.value)) {
        email.setCustomValidity("aaa")
        if (errors.indexOf(emailMsg) === -1)
            errors.push(emailMsg)
    } else {
        email.setCustomValidity("")
        console.log("email valid")
    }

    if (!isPsswd(psswd.value)) {
        psswd.setCustomValidity("bbb")
        if (errors.indexOf(psswdMsg) === -1)
            errors.push(psswdMsg)
    } else {
        psswd.setCustomValidity("")
        console.log("password valid")
    }

    if (errors.length > 0) {
        console.log(errors)
        elem.style.display = "block"
        elem.className = "alert"
        elem.innerHTML = errors.join("<br>")
    }

    /*if (event.target.type === "email") {
    console.log(eventTarget.type)
    regExp = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/
    if (regExp.test(eventTarget.value)) {
        console.log("Success")
    } else {
        console.log("input not valid")
        errors.push("Errata composizione dell'email, deve contenere " +
            "almeno 'nome'@'dominio'");
    }
} else if (event.target.type === "password") {
    console.log(eventTarget.type)
    console.log("mocc a ktm")
    regExp = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).$/
    if (regExp.test(eventTarget.value)) {
        console.log("pssw success")
    } else {
        event.target.setCustomValidity("mokkammammt")
        console.log("pssw not valid")
        errors.push("La password deve essere composta bene")
    }
}*/
}

function reset() {
    const elem = document.getElementById("alert-box")
    elem.style.display = "none";
    errors = []
}

/*const emailInput = document.querySelector("#email")
const btn = document.querySelector("#btn-sbmit")

emailInput.addEventListener('input', function(event) {
    console.log("call" + event)
    const target = event.target;
    const expPattern = /^\w{1,5}$/
    console.log(target.value)

    if(expPattern.test(target.value)){
        console.log("success")
        btn.disabled = false;
    }else{
        btn.disabled = true;
    }

    console.log(expPattern.test(target.value))
})

function patternEmail(event){
    console.log("call" + event)
    const target = event.target;
    const expPattern = new RegExp()
    console.log(target.textContent + " " + target.value)
    console.log(expPattern.test(target.textContent))
}*/