// index.js

function adminLogin() {
  openModal("adminLogin");
}

document.addEventListener("DOMContentLoaded", function () {
  const _adminBtn = document.getElementById("adminLogin");

  if (_adminBtn) {
    _adminBtn.addEventListener("click", adminLogin);
  }

  const _doctorBtn = document.getElementById("doctorLogin");

  if (_doctorBtn) {
    _doctorBtn.addEventListener("click", doctorLogin);
  }

  const _patientBtn = document.getElementById("patientLogin");

  if (_patientBtn) {
    _patientBtn.addEventListener("click", patientLogin);
  }
});

function doctorLogin() {
  openModal("doctorLogin");
}

function adminLoginHandler() {
  const username = document.getElementById("username");
  const password = document.getElementById("password");
  loginAdmin(username.value, password.value)
    .then((res) => {
      if (res.success) {
        localStorage.setItem("token", res.message);
        localStorage.setItem("userRole", "admin");
        // Redirect to admin dashboard
        window.location.href = `/adminDashboard/${res.message}`;
      } else {
        alert("Login failed: " + res.message);
      }
    })
    .catch((err) => {
      console.error({ err });
      alert("Login error: " + err.message);
    });
}

async function loginAdmin(username, password) {
  try {
    // const response = await fetch(`${APPOINTMENT_API}/${token}`, {
    const response = await fetch(`http://localhost:8080/admin/login`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        username,
        password,
      }),
    });

    const data = await response.json();

    return {
      success: response.ok,
      message: data.token || "-",
    };
  } catch (error) {
    console.error("Error while booking appointment:", error);
    return {
      success: false,
      message: "Network error. Please try again later.",
    };
  }
}

window.adminLoginHandler = adminLoginHandler;
// window.signupPatient = signupPatient;
// window.loginPatient = loginPatient;
window.adminAddDoctorHandler = adminAddDoctorHandler;
window.doctorLoginHandler = doctorLoginHandler;

async function adminAddDoctorHandler() {
  // Placeholder for adding doctor
  alert("Add doctor functionality not implemented yet");
}

function doctorLoginHandler() {
  const email = document.getElementById("email");
  const password = document.getElementById("password");

  loginDoctor(email.value, password.value)
    .then((res) => {
      if (res.success) {
        localStorage.setItem("token", res.message);
        // Redirect to doctor dashboard
        window.location.href = `/doctorDashboard/${res.message}`;
      } else {
        alert("Login failed: " + res.message);
      }
    })
    .catch((err) => {
      console.error({ err });
      alert("Login error: " + err.message);
    });
}

async function loginDoctor(email, password) {
  const auth = {
    email,
    password,
  };

  try {
    const response = await fetch(`http://localhost:8080/doctor/login`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        email,
        password,
      }),
    });

    const data = await response.json();

    return {
      success: response.ok,
      message: data.token || "-",
    };
  } catch (error) {
    console.error("Error while logging in doctor:", error);
    return {
      success: false,
      message: "Network error. Please try again later.",
    };
  }
}

/*
  Import the openModal function to handle showing login popups/modals
  Import the base API URL from the config file
  Define constants for the admin and doctor login API endpoints using the base URL

  Use the window.onload event to ensure DOM elements are available after page load
  Inside this function:
    - Select the "adminLogin" and "doctorLogin" buttons using getElementById
    - If the admin login button exists:
        - Add a click event listener that calls openModal('adminLogin') to show the admin login modal
    - If the doctor login button exists:
        - Add a click event listener that calls openModal('doctorLogin') to show the doctor login modal


  Define a function named adminLoginHandler on the global window object
  This function will be triggered when the admin submits their login credentials

  Step 1: Get the entered username and password from the input fields
  Step 2: Create an admin object with these credentials

  Step 3: Use fetch() to send a POST request to the ADMIN_API endpoint
    - Set method to POST
    - Add headers with 'Content-Type: application/json'
    - Convert the admin object to JSON and send in the body

  Step 4: If the response is successful:
    - Parse the JSON response to get the token
    - Store the token in localStorage
    - Call selectRole('admin') to proceed with admin-specific behavior

  Step 5: If login fails or credentials are invalid:
    - Show an alert with an error message

  Step 6: Wrap everything in a try-catch to handle network or server errors
    - Show a generic error message if something goes wrong


  Define a function named doctorLoginHandler on the global window object
  This function will be triggered when a doctor submits their login credentials

  Step 1: Get the entered email and password from the input fields
  Step 2: Create a doctor object with these credentials

  Step 3: Use fetch() to send a POST request to the DOCTOR_API endpoint
    - Include headers and request body similar to admin login

  Step 4: If login is successful:
    - Parse the JSON response to get the token
    - Store the token in localStorage
    - Call selectRole('doctor') to proceed with doctor-specific behavior

  Step 5: If login fails:
    - Show an alert for invalid credentials

  Step 6: Wrap in a try-catch block to handle errors gracefully
    - Log the error to the console
    - Show a generic error message
*/
