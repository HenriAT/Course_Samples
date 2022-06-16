%{
Funktio ratkaisee DY-ryhmän y'(t) = f(t, y(t)) neljännen kertaluvun
RK-menetlemällä. Funktio palauttaa matriisin, jonka ensimmäisellä rivillä
ovat tasavälein jaotellut ajanhetket t ja seuraavilla riveillä y(t):n
komponenttien arvot ajanhetkillä t.

a = t:n alkuarvo
b = t:n loppuarvo
f = vektoriarvoinen funktio yhtälössä y'(t) = f(t, y(t))
y0 = y(t):n alkuarvo
nsteps = tasaisesti jaoteltujen ajanhetkien lukumäärä
%}


function A = rk4(a, b, f, y0, nsteps)
    t = linspace(a, b, nsteps);
    h = (b - a) / (nsteps - 1);
    A = zeros(size(y0, 1) + 1, nsteps);
    A(1,:) = t;
    y = y0;
    for i = 1:nsteps
        A(2:size(y0, 1) + 1, i) = y;
        k1 = h .* f(t(i), y);
        k2 = h .* f(t(i) + h / 2, y + k1 ./ 2);
        k3 = h .* f(t(i) + h / 2, y + k2 ./ 2);
        k4 = h .* f(t(i) + h, y + k3);
        y = y + (1/6) .* k1 + (1/3) .* k2 + (1/3) .* k3 + (1/6) .* k4;
    end
end