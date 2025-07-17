
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

function toggleFaq(anchor) {
    const answer = anchor.nextElementSibling;
    answer.classList.toggle("show");
  }
  
  //add product page 
  document.getElementById('imageFile').addEventListener('change', function(){
    const fileName = this.files[0]?.name || "No file chosen";
    document.getElementById('file-name-image').textContent = fileName;
  });

  document.getElementById('pdfFile').addEventListener('change', function(){
    const fileName = this.files[0]?.name || "No file chosen";
    
	document.getElementById('file-name-pdf').textContent = fileName;
  });
  