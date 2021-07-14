const formElement = document.querySelector("#registrationForm")
formElement.setAttribute('novalidate', 'true')

formElement.addEventListener('submit', function (event) {
    if (formElement.checkValidity()) {
        formElement.submit()
    } else {
        event.preventDefault()
    }
})


let errors = []
const entries = formElement.elements

for (let i = 0; i < entries.length; i++) {
    let validInput = entries[i].nodeName.match('INPUT')
    if (entries[i].willValidate && validInput) {
        console.log(entries[i])
        entries[i].addEventListener('input', reportError)
    }
}

function checkEmail(param) {
    return /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(param)
}

function checkPassword(param) {
    return /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[._-])[A-Za-z\d._-]{8,16}$/.test(param)
}

function checkZIPCode(param) {
    return /^(\d){5}$/.test(param)
}

function checkPhoneNumber(param) {
    return /^(\d){10}$/.test(param)
}

const email = document.getElementsByName("fieldEmail")[0]
const psswd = document.getElementsByName("fieldPassword")[0]
const name = document.getElementById('name')
const surname = document.getElementById('surname')
const phoneNumber = document.getElementById('phoneNumber')
const zipCode = document.getElementById('zipcode')
const alertElement = document.getElementById('alert-box')

function reportError() {
    const emailMsg = '&#9898;Email non valida'
    const psswdMsg = '&#9898; Password non valida. Composizione: almeno una lettera maiuscola,' +
        ' almeno una minuscola, almeno un numero ed un carattere speciale (.-_)'
    const phoneMsg = '&#9898; Numero di telefono non valido'
    const zipCodeMsg = '&#9898; CAP non valido'

    console.log("Email: " + email.value)
    console.log("Password: " + psswd.value)
    console.log("Name: " + name.value)
    console.log("Surname: " + surname.value)
    console.log("zip: " + zipCode.value)

    if (!checkPhoneNumber(phoneNumber.value)) {
        console.log("phone invalid")
        psswd.setCustomValidity("phone number invalid")
        if (!errors.includes(phoneMsg))
            errors.push(phoneMsg)
    } else {
        psswd.setCustomValidity('')
        errors.splice(errors.indexOf(phoneMsg), 1)
        console.log("phone valid")
    }

    if (!checkPassword(psswd.value)) {
        console.log("password invalid")
        psswd.setCustomValidity("Password invalid")
        if (!errors.includes(psswdMsg))
            errors.push(psswdMsg)
    } else {
        psswd.setCustomValidity("")
        errors.splice(errors.indexOf(psswdMsg), 1)
        console.log("password valid")
    }

    /*if (!checkSurname(surname.value)) {
        surname.setCustomValidity('surname invalid')
        if (!errors.includes(surnameMsg))
            errors.push(surnameMsg)
    } else {
        surname.setCustomValidity('')
        errors.splice(errors.indexOf(surnameMsg), 1)
        console.log('surname valid')
    }*/


    if (!checkEmail(email.value)) {
        email.setCustomValidity('Email invalid')
        if (!errors.includes(emailMsg))
            errors.push(emailMsg)
    } else {
        email.setCustomValidity("")
        errors.splice(errors.indexOf(emailMsg), 1)
        console.log("email valid")
    }

    /*if (!checkName(name.value)) {
        name.setCustomValidity('name invalid')
        if (!errors.includes(nameMsg))
            errors.push(nameMsg)
    } else {
        name.setCustomValidity('')
        errors.splice(errors.indexOf(nameMsg), 1)
        console.log('name valid')
    }*/

    if (!checkZIPCode(zipCode.value)) {
        console.log("zip invalid")
        zipCode.setCustomValidity('zipcode invalid')
        if (!errors.includes(zipCodeMsg))
            errors.push(zipCodeMsg)
    } else {
        zipCode.setCustomValidity('')
        errors.splice(errors.indexOf(zipCodeMsg), 1)
        console.log('zipcode valid')
    }

    if (errors.length > 0) {
        console.log(errors);
        alertElement.style.display = "block";
        alertElement.className = "alert";
        alertElement.innerHTML = errors.join("<br>");
    } else {
        alertElement.innerHTML = ""
        alertElement.style.display = "none"
    }
}