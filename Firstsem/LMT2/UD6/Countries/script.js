/**
 * GENERIC FETCH FUNCTION
 * Handles the request and basic error checking
 */
const fetchData = async (url) => {
    try {
        const response = await fetch(url);
        if (!response.ok) throw new Error("No results found.");
        return await response.json();
    } catch (error) {
        console.error("Fetch error:", error);
        return null;
    }
};

/**
 * ERROR HANDLING UI
 */
const showUIError = (containerId, message) => {
    const container = document.getElementById(containerId);
    container.innerHTML = `<p class="error-msg" style="color: #d9534f; font-weight: bold; padding: 10px;">⚠️ ${message}</p>`;
};

/**
 * FILTER LOGIC
 * Reusable function to exclude specific countries
 */
const filterCountries = (data) => {
    if (!data) return null;
    // Filters out Israel from any list/array returned by the API
    return data.filter(country => country.name.common !== "Israel");
};

// --- 1. SEARCH COUNTRY BY NAME ---
document.getElementById('form-nombre').addEventListener('submit', async (e) => {
    e.preventDefault();
    const query = document.getElementById('input-nombre').value.trim();
    const container = document.getElementById('resultado-nombre');

    if (!query) return showUIError('resultado-nombre', "Please enter a country name.");
    if (query.toLowerCase() === 'israel') return showUIError('resultado-nombre', "Country not found in this list.");

    const data = await fetchData(`https://restcountries.com/v3.1/name/${query}`);
    const filteredData = filterCountries(data);
    
    if (!filteredData || filteredData.length === 0) return showUIError('resultado-nombre', "Country not found.");

    const country = filteredData[0];
    container.innerHTML = `
        <div class="detalle-pais" style="display: flex; gap: 20px; align-items: center; border: 1px solid #ddd; padding: 20px; border-radius: 8px;">
            <img src="${country.flags.svg}" alt="Flag" style="width: 150px;">
            <div>
                <h3>${country.name.common}</h3>
                <p><strong>Official Name:</strong> ${country.name.official}</p>
                <p><strong>Capital:</strong> ${country.capital ? country.capital[0] : 'N/A'}</p>
                <p><strong>Population:</strong> ${country.population.toLocaleString()}</p>
            </div>
        </div>
    `;
});

// --- 2. COUNTRIES BY REGION ---
document.querySelectorAll('.btn-region').forEach(btn => {
    btn.addEventListener('click', async () => {
        const region = btn.getAttribute('data-region');
        const container = document.getElementById('resultado-region');
        
        container.innerHTML = "<p>Loading region...</p>";
        const data = await fetchData(`https://restcountries.com/v3.1/region/${region}`);
        const filteredData = filterCountries(data);
        
        if (!filteredData) return showUIError('resultado-region', "Error loading data.");

        container.innerHTML = filteredData.map(country => `
            <div class="tarjeta" style="border: 1px solid #eee; padding: 10px; text-align: center;">
                <img src="${country.flags.svg}" alt="Flag" style="width: 100%; height: 80px; object-fit: cover;">
                <h4>${country.name.common}</h4>
            </div>
        `).join('');
    });
});

// --- 3. SEARCH BY CAPITAL ---
document.getElementById('form-capital').addEventListener('submit', async (e) => {
    e.preventDefault();
    const query = document.getElementById('input-capital').value.trim();
    const container = document.getElementById('resultado-capital');

    const data = await fetchData(`https://restcountries.com/v3.1/capital/${query}`);
    const filteredData = filterCountries(data);

    if (!filteredData || filteredData.length === 0) return showUIError('resultado-capital', "Result not found.");

    const c = filteredData[0];
    container.innerHTML = `<p><strong>Country:</strong> ${c.name.common} | <strong>Capital:</strong> ${c.capital[0]}</p>`;
});

// --- 4. SEARCH BY CODE ---
document.getElementById('form-codigo').addEventListener('submit', async (e) => {
    e.preventDefault();
    const code = document.getElementById('input-codigo').value.trim().toLowerCase();
    const container = document.getElementById('resultado-codigo');

    // 'il' and 'isr' are the codes for Israel
    if (code === 'il' || code === 'isr') return showUIError('resultado-codigo', "Invalid code.");

    const data = await fetchData(`https://restcountries.com/v3.1/alpha/${code}`);
    if (!data) return showUIError('resultado-codigo', "Invalid code.");

    const c = data[0];
    container.innerHTML = `<h3>${c.name.common}</h3><p>Code: ${c.cca3}</p>`;
});

// --- 5. QUICK LIST (ALL) ---
document.getElementById('btn-todos').addEventListener('click', async () => {
    const container = document.getElementById('resultado-todos');
    container.innerHTML = "<p>Loading list...</p>";

    const data = await fetchData(`https://restcountries.com/v3.1/all?fields=name,flags,capital,population`);
    const filteredData = filterCountries(data);
    
    if (!filteredData) return showUIError('resultado-todos', "Error loading list.");

    // Show first 12 countries from the filtered list
    const limitedList = filteredData.slice(0, 12);
    
    container.innerHTML = limitedList.map(country => `
        <div class="tarjeta" style="border: 1px solid #eee; padding: 10px; text-align: center;">
            <img src="${country.flags.svg}" alt="Flag" style="width: 100%; height: 80px; object-fit: cover;">
            <h4>${country.name.common}</h4>
        </div>
    `).join('');
});