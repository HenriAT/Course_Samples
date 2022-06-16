%{
Funktio ratkaisee DY-ryhmän y'(t) = f(t, y(t)) Milnen menetelmällä. Funktio palauttaa matriisin,
jonka ensimmäisellä rivillä ovat tasavälein jaotellut ajanhetket t ja seuraavilla riveillä y(t):n
kompotenttien arvot ajanhetkillä t.

a = t:n alkuarvo
b = t:n loppuarvo
f = vektoriarvoinen funktio yhtälössä y'(t) = f(t, y(t))
y0 = y(t):n alkuarvo
nsteps = tasaisesti jaoteltujen ajanhetkien lukumäärä (tämän täytyy olla
vähintään 5, sillä alustukseen tarvitaan 4 aikaisempaa pistettä)
%}

function A = milne(a, b, f, y0, nsteps)
   t = linspace(a, b, nsteps);
   h = (b-a)/(nsteps-1);
   B = rk4(a, 3 * h, f, y0, 4);
   k = size(y0,1);
   y = zeros(k,nsteps);
   y(:,1:4) = B(2:k+1,1:4);
   for i = 5:nsteps
       ye = y(:,i-4) + (4*h/3) .* (2 .* f(t(i-3),y(:,i-3)) - f(t(i-2),y(:,i-2)) + 2 .* f(t(i-1),y(:,i-1)));
       y(:,i) = y(:,i-2) + (h/3) .* (f(t(i-2),y(:,i-2)) + 4 .* f(t(i-1),y(:,i-1)) + f(t(i), ye));
   end
   A = zeros(k+1, nsteps);
   A(1,:) = t;
   A(2:k+1,:) = y;
end