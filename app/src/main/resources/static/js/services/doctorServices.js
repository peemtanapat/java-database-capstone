import { API_BASE_URL } from "../config/config.js";
const DOCTOR_API = API_BASE_URL + "/doctors";

window.getDoctors = async function getDoctors() {
  try {
    const response = await fetch(`${DOCTOR_API}`, {
      method: "GET",
    });
    const result = await response.json();
    if (!response.ok) {
      throw new Error(result.message);
    }
    return { success: response.ok, message: result.message };
  } catch (error) {
    console.error("Error :: getDoctors :: ", error);
    return { success: false, message: error.message };
  }
};

window.saveDoctor = async function saveDoctor(doctor, token) {
  try {
    const response = await fetch(`${DOCTOR_API}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + token,
      },
      body: JSON.stringify(doctor),
    });

    const result = await response.json();

    if (!response.ok) {
      throw new Error(result.message);
    }

    return { success: response.ok, message: result.message };
  } catch (error) {
    console.error("Error :: saveDoctor :: ", error);
    return { success: false, message: error.message };
  }
};

window.filterDoctors = async function filterDoctors(type, value) {
  try {
    let url = `${DOCTOR_API}/filter`;
    const params = new URLSearchParams();

    if (value) {
      if (type === "name") {
        params.append("name", value);
      } else if (type === "specialty") {
        params.append("specialty", value);
      } else if (type === "availableTimes") {
        params.append("availableTimes", value);
      } else if (type === "email") {
        params.append("email", value);
      } else if (type === "phoneNumber") {
        params.append("phoneNumber", value);
      }
    }

    if (params.toString()) {
      url += `?${params.toString()}`;
    }
    const response = await fetch(url, {
      method: "GET",
    });
    const result = await response.json();

    if (!response.ok) {
      throw new Error(result.message || "Failed to filter doctors");
    }
    return { success: response.ok, doctors: result };
  } catch (error) {
    console.error("Error :: filterDoctors :: ", error);
    return { success: false, doctors: [] };
  }
};

/*
  Import the base API URL from the config file
  Define a constant DOCTOR_API to hold the full endpoint for doctor-related actions
  

  Function: getDoctors
  Purpose: Fetch the list of all doctors from the API

   Use fetch() to send a GET request to the DOCTOR_API endpoint
   Convert the response to JSON
   Return the 'doctors' array from the response
   If there's an error (e.g., network issue), log it and return an empty array


  Function: deleteDoctor
  Purpose: Delete a specific doctor using their ID and an authentication token

   Use fetch() with the DELETE method
    - The URL includes the doctor ID and token as path parameters
   Convert the response to JSON
   Return an object with:
    - success: true if deletion was successful
    - message: message from the server
   If an error occurs, log it and return a default failure response


  Function: saveDoctor
  Purpose: Save (create) a new doctor using a POST request

   Use fetch() with the POST method
    - URL includes the token in the path
    - Set headers to specify JSON content type
    - Convert the doctor object to JSON in the request body

   Parse the JSON response and return:
    - success: whether the request succeeded
    - message: from the server

   Catch and log errors
    - Return a failure response if an error occurs


    */
