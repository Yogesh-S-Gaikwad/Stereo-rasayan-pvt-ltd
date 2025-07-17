function sendEmailOtp() {
    const email = document.getElementById("email").value.trim();
    const btn = document.querySelector(".sendOtpBtn");
    const signupModal = document.getElementById("signupModal");
    const otpSection = document.getElementById("otpSection");

    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!email) {
        alert("Please enter your email first.");
        return;
    }

    if (!emailRegex.test(email)) {
        alert("Please enter a valid email address.");
        return;
    }

    btn.disabled = true;
    btn.textContent = "Sending...";

    fetch(`/send-email-otp?email=${encodeURIComponent(email)}`)
        .then(response => {
            if (!response.ok) {
                throw new Error("Failed to send OTP");
            }
            return response.text();
        })
        .then(data => {
            // ✅ OTP sent
            alert("✅ OTP sent to your email.");

            // Hide signup, show OTP section
            signupModal.style.display = "none";
            otpSection.style.display = "flex";

            btn.disabled = false;
            btn.textContent = "Resend OTP";
        })
        .catch(err => {
            // ❌ OTP failed
            alert("❌ Failed to send OTP. Please try again.");
            btn.disabled = false;
            btn.textContent = "Send OTP";
        });
}



  const urlParams = new URLSearchParams(window.location.search);
  const success = urlParams.get("success");

  if (success) {
    if (success === "admin") {
      alert("Welcome Admin!");
    } else if (success === "user") {
      alert("Welcome User!");
    } else if (success === "error") {
      alert("Invalid credentials. Please try again.");
      loginModal.style.display = "flex";
      document.getElementById("loginErrorMessage").style.display = "flex";  
    }
  }

  const signupForm = document.getElementById("signupForm");

  if (signupForm) {
    signupForm.addEventListener("submit", function (e) {
      if (!signupForm.checkValidity()) {
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
});
