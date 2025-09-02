window.adminAddDoctorHandler = async function adminAddDoctor() {
  const token = localStorage.getItem("token");

  const doctorName = document.getElementById("doctorName");
  const doctorEmail = document.getElementById("doctorEmail");
  const doctorPassword = document.getElementById("doctorPassword");
  const doctorPhone = document.getElementById("doctorPhone");
  const specialization = document.getElementById("specialization");
  const availableTimes = Array.from(
    document.querySelectorAll('input[name="availability"]:checked')
  ).map((checkedChoice) => checkedChoice.value);

  const doctor = {
    name: doctorName.value,
    email: doctorEmail.value,
    password: doctorPassword.value,
    phoneNumber: doctorPhone.value,
    specialty: specialization.value,
    availableTimes,
  };

  await saveDoctor(doctor, token);
  window.location.href = `/adminDashboard/${token}`;
};

function openModalAddDoctor() {
  openModal("addDoctor");
}

document.addEventListener("DOMContentLoaded", function () {
  const _addDocBtn = document.getElementById("addDocBtn");

  if (_addDocBtn) {
    _addDocBtn.addEventListener("click", openModalAddDoctor);
  }
});
document.addEventListener("DOMContentLoaded", function () {
  const saveDoctorBtn = document.getElementById("saveDoctorBtn");

  if (saveDoctorBtn) {
    saveDoctorBtn.addEventListener("click", adminAddDoctorHandler);
  }
});

// Add event listener for search bar
document.addEventListener("DOMContentLoaded", function () {
  const searchBar = document.getElementById("searchBar");

  if (searchBar) {
    searchBar.addEventListener("input", filterDoctorsOnChange);
  }

  // Add event listener for filter type
  const filterType = document.getElementById("filterType");
  if (filterType) {
    filterType.addEventListener("change", updateSearchPlaceholder);
    filterType.addEventListener("change", filterDoctorsOnChange);
  }

  // Set initial placeholder
  updateSearchPlaceholder();

  // Load initial doctor list
  loadDoctorList();
});

function loadDoctorList() {
  filterDoctors("name", null)
    .then((response) => {
      const doctors = response.doctors || [];
      renderDoctorTable(doctors);
    })
    .catch((error) => {
      console.error("Failed to load doctors:", error);
    });
}

function updateSearchPlaceholder() {
  const filterType = document.getElementById("filterType");
  const searchBar = document.getElementById("searchBar");

  if (filterType && searchBar) {
    const selected = filterType.value;
    if (selected === "name") {
      searchBar.placeholder = "Search doctors by name";
    } else if (selected === "specialty") {
      searchBar.placeholder = "Search doctors by specialty";
    } else if (selected === "availableTimes") {
      searchBar.placeholder = "Search doctors by available times (e.g., 09:00)";
    } else if (selected === "email") {
      searchBar.placeholder = "Search doctors by email";
    } else if (selected === "phoneNumber") {
      searchBar.placeholder = "Search doctors by phone number";
    }
  }
}

function filterDoctorsOnChange() {
  const searchBar = document.getElementById("searchBar");
  const filterType = document.getElementById("filterType");
  const value = searchBar.value.trim();
  const type = filterType.value;

  filterDoctors(type, value)
    .then((response) => {
      const doctors = response.doctors || [];
      renderDoctorTable(doctors);
    })
    .catch((error) => {
      console.error("Failed to filter doctors:", error);
      alert("❌ An error occurred while filtering doctors.");
    });
}
// Function: adminAddDoctor
//   Purpose: Collect form data and add a new doctor to the system

//     Collect input values from the modal form
//     - Includes name, email, phone, password, specialty, and available times

//     Retrieve the authentication token from localStorage
//     - If no token is found, show an alert and stop execution

//     Build a doctor object with the form values

//     Call saveDoctor(doctor, token) from the service

//     If save is successful:
//     - Show a success message
//     - Close the modal and reload the page

//     If saving fails, show an error message
function renderDoctorTable(doctors) {
  const tbody = document.getElementById("doctorTableBody");
  tbody.innerHTML = "";

  if (doctors.length > 0) {
    doctors.forEach((doctor) => {
      const tr = document.createElement("tr");
      tr.innerHTML = `
        <td>${doctor.name}</td>
        <td>${doctor.specialty}</td>
        <td>${doctor.email}</td>
        <td>${doctor.phoneNumber}</td>
        <td>
          <button class="action-btn" onclick="editDoctor('${doctor.id}')">Edit</button>
          <button class="action-btn" onclick="deleteDoctor('${doctor.id}')">Delete</button>
        </td>
      `;
      tbody.appendChild(tr);
    });
  } else {
    const tr = document.createElement("tr");
    tr.innerHTML = `<td colspan="5" style="text-align: center;">No doctors found.</td>`;
    tbody.appendChild(tr);
  }
}

// Placeholder functions for edit and delete
function editDoctor(id) {
  alert(`Edit doctor with ID: ${id}`);
}

function deleteDoctor(id) {
  if (confirm(`Are you sure you want to delete doctor with ID: ${id}?`)) {
    alert(`Delete doctor with ID: ${id}`);
  }
}
