document.addEventListener("DOMContentLoaded", function () {
  // ---------- Login/Signup Modal Logic ----------
  const loginLink = document.getElementById("loginLink");
  const showSignup = document.getElementById("showSignup");
  const loginModal = document.getElementById("loginModal");
  const signupModal = document.getElementById("signupModal");
  const closeLogin = document.getElementById("closeLogin");
  const closeSignup = document.getElementById("closeSignup");
  const goToLogin = document.getElementById("goTologin");


    loginLink.onclick = () => loginModal.style.display = "flex";

    showSignup.onclick = (e) => {
      e.preventDefault();
      loginModal.style.display = "none";
      signupModal.style.display = "flex";
    };

  // Go to login from signup modal

    goToLogin.onclick = (e) => {
      e.preventDefault();
      signupModal.style.display = "none";
      loginModal.style.display = "flex";
    };


  // Close login modal
 
    closeLogin.onclick = () => loginModal.style.display = "none";
  

  // Close signup modal

    closeSignup.onclick = () => signupModal.style.display = "none";
  

  // Close modals when clicking outside the content
  window.onclick = (e) => {
    if (e.target === loginModal) loginModal.style.display = "none";
    if (e.target === signupModal) signupModal.style.display = "none";
  };

  // Show alert and login modal based on URL parameter
  const urlParams = new URLSearchParams(window.location.search);
  const success = urlParams.get("success");

  if (success) {
    if (success === "admin") {
      alert("Welcome Admin!");l
    } else if (success === "user") {
      alert("Welcome User!");

    } else if (success === "error") {
      alert("Invalid credentials. Please try again.");
      document.getElementById("loginModal").style.display = "flex";
	  document.getElementById("loginErrorMessage").style.display = "flex";  
	  
    }
  }

  // ---------- Signup Form Validation ----------
  const signupForm = document.getElementById("signupForm");

  if (signupForm) {
    signupForm.addEventListener("submit", function (e) {
      if (!signupForm.checkValidity()) {
        // Let browser show validation messages
        return;
      }

      const email = signupForm.email.value.trim();
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

      if (!emailRegex.test(email)) {
        e.preventDefault();
        alert("⚠️ Please enter a valid email address.");
      }
    });
  }

  // ---------- Email Subscription Form ----------
  const form = document.getElementById("subscribeForm");

  if (form) {
    form.addEventListener("submit", function (e) {
      e.preventDefault();

      const emailInput = document.getElementById("emailInput");
      const email = emailInput.value.trim();
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

      if (!emailRegex.test(email)) {
        alert("⚠️ Please enter a valid email address.");
        return;
      }

      fetch("/subscribe", {
        method: "POST",
        headers: {
          "Content-Type": "application/x-www-form-urlencoded"
        },
        body: `email=${encodeURIComponent(email)}`
      })
        .then(response => {
          if (response.ok) {
            alert("✅ Subscription successful!");
            emailInput.value = "";
          } else {
            alert("❌ Subscription failed.");
          }
        })
        .catch(error => {
          console.error(error);
          alert("⚠️ Something went wrong.");
        });
    });
  }



// ---------- Global Function for OTP ----------
function sendEmailOtp() {
  const emailField = document.getElementById("email");
  if (!emailField) {
    alert("Email field not found.");
    return;
  }

  const email = emailField.value.trim();
  if (!email) {
    alert("⚠️ Please enter your email.");
    return;
  }

  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!emailRegex.test(email)) {
    alert("⚠️ Enter a valid email address.");
    return;
  }

  fetch(`/send-email-otp?email=${encodeURIComponent(email)}`)
    .then(response => {
      if (response.ok) {
        alert("✅ OTP sent to your email.");
        const otpSection = document.getElementById("otpSection");
        if (otpSection) {
          otpSection.style.display = "block";
        }
      } else {
        alert("❌ Failed to send OTP.");
      }
    })
    .catch(error => {
      console.error("Error sending OTP:", error);
      alert("⚠️ Something went wrong while sending OTP.");
    });
}
  // ---------- Product Quantity Counter ----------
  let count = 1;
  const countSpan = document.getElementById("count");

  function updateCountDisplay() {
    if (countSpan) {
      countSpan.textContent = count;
    }
  }

  window.increment = function () {
    count++;
    updateCountDisplay();
  };

  window.decrement = function () {
    if (count > 1) {
      count--;
      updateCountDisplay();
    }
  };

  updateCountDisplay(); 
});
function toggleFaq(anchor) {
    const answer = anchor.nextElementSibling;
    answer.classList.toggle("show");
  }