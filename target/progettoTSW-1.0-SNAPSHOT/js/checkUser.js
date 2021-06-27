const formElement = document.forms[1]
formElement.setAttribute('novalidate', 'true')

formElement.addEventListener('submit', function (event) {
    if (formElement.checkValidity()) {
        console.log("Success")
    } else {
        event.preventDefault();
    }
})

function reset(event) {
    event.target.nextElementSibling.textContent = ''
}

const entries = formElement.elements
for (let i = 0; i < entries.length; ++i) {
    let validInput = entries[i].nodeName.match('INPUT|TEXTAREA|SELECT')
    if (entries[i].willValidate !== undefined && validInput) {
        entries[i].addEventListener('invalid', reportError)
        entries[i].addEventListener('focus', reset)
    }
}


function reportError(event) {
    const eventTarget = event.target
    const nextElem = eventTarget.nextElementSibling
    const errors = [];
    const regExp = /^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/
    console.log(eventTarget.type)

    if (event.target.type === "email") {
        if (regExp.test(eventTarget.value)) {
            console.log("Success")
        } else {
            console.log("input not valid")
            errors.push("Email invalid");
        }
    }

    console.log(errors)
    nextElem.textContent = errors.join("-")
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