document.addEventListener("DOMContentLoaded", function () {

    const sidebar = document.getElementById("sidebar");
    const overlay = document.getElementById("sidebarOverlay");
    const toggleBtn = document.getElementById("menuToggle");

    if (!sidebar || !overlay) return;

    function openSidebar() {
        sidebar.classList.remove("-translate-x-full");
        overlay.classList.remove("hidden");
    }

    function closeSidebar() {
        sidebar.classList.add("-translate-x-full");
        overlay.classList.add("hidden");
    }

    toggleBtn?.addEventListener("click", function () {
        const isOpen = !sidebar.classList.contains("-translate-x-full");

        if (isOpen) closeSidebar();
        else openSidebar();
    });

    overlay.addEventListener("click", closeSidebar);
});