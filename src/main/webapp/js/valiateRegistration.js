const formElement = document.querySelector("#registrationForm")
formElement.setAttribute('novalidate', 'true')

formElement.addEventListener('submit', function (event) {
    if (formElement.checkValidity()) {
        console.log("Success")
        formElement.submit()
    } else {
        console.log("Prevent default")
        event.preventDefault()
    }
})


let errors = []
const entries = formElement.elements
console.log(entries.length)
for (let i = 0; i < entries.length; i++) {
    let validInput = entries[i].nodeName.match('INPUT')
    if (entries[i].willValidate && validInput) {
        console.log(entries[i])
        entries[i].addEventListener('input', reportError)
        //entries[i].addEventListener('focus', reset)
    }
}

function checkSurname(param) {
    return /^([a-zA-Z\s]){3,25}$/.test(param)
}

function checkStreet(param) {
    return /^$/.test(param)
}

function checkName(param) {
    return /^([a-zA-Z\s]){3,25}$/.test(param)
}

function isEmail(param) {
    return /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(param)
}

function isPsswd(param) {
    return /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[._-])[A-Za-z\d._-]{8,16}$/.test(param)
}

function checkZIPCode(param) {
    return /^(\d){5}$/.test(param)
}

const email = document.getElementsByName("fieldEmail")[0]
const psswd = document.getElementsByName("fieldPassword")[0]
const name = document.getElementById('name')
const surname = document.getElementById('surname')
const dateOfBirth = document.getElementById('date_birth')
const phoneNumber = document.getElementById('phoneNumber')
const street = document.getElementById('street')
const city = document.getElementById('city')
const zipCode = document.getElementById('zipcode')


function reportError() {
    const alertElement = document.getElementById('alert-box')
    const emailMsg = '&#9898;Email non valida'
    const psswdMsg = '&#9898; Password non valida. Composizione: almeno una lettera maiuscola,' +
        ' almeno una minuscola, almeno un numero ed un carattere speciale (.-_)'
    const nameMsg = '&#9898; Nome non valido'
    const surnameMsg = '&#9898; Cognome non valido'
    const streetMsg = '&#9898; Indirizzo non valido'
    const phoneMsg = '&#9898; Numero di telefono non valido'
    const zipCodeMsg = '&#9898; CAP non valido'

    if (!checkSurname(surname.value)) {
        surname.setCustomValidity('surname invalid')
        if (errors.indexOf(surnameMsg) === -1)
            errors.push(surnameMsg)
    } else {
        surname.setCustomValidity('')
        errors.splice(errors.indexOf(surnameMsg), 1)
        console.log('surname valid')
    }

    if (!isPsswd(psswd.value)) {
        psswd.setCustomValidity("Password invalid")
        if (errors.indexOf(psswdMsg) === -1)
            errors.push(psswdMsg)
    } else {
        psswd.setCustomValidity("")
        errors.splice(errors.indexOf(psswdMsg), 1)
        console.log("password valid")
    }

    if (!isEmail(email.value)) {
        email.setCustomValidity('Email invalid')
        if (errors.indexOf(emailMsg) === -1)
            errors.push(emailMsg)
    } else {
        email.setCustomValidity("")
        errors.splice(errors.indexOf(emailMsg), 1)
        console.log("email valid")
    }

    if (!checkName(name.value)) {
        name.setCustomValidity('name invalid')
        if (errors.indexOf(nameMsg) === -1)
            errors.push(nameMsg)
    } else {
        name.setCustomValidity('')
        errors.splice(errors.indexOf(nameMsg), 1)
        console.log('name valid')
    }

    if (!checkZIPCode(zipCode.value)) {
        zipCode.setCustomValidity('zipcode invalid')
        if (errors.indexOf(zipCodeMsg) === -1)
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
    }
}

function reset() {
    const elem = document.getElementById("alert-box")
    elem.style.display = "none";
    errors = []
}