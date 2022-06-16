%{
Funktio toteuttaa tarvittavan haarukoinnin.
%}

function z = haarukointi(a, b, epsilon, itmax)
    z1 = a;
    z2 = b;
    f = @(x, y) [y(2); sin(x^2)];
    for i = 1:itmax
        z = (z1 + z2) / 2;
        A = ode45(f, [0 1], [0; z]);
        r = A.y(1,size(A.y, 2));
        if abs(r) < epsilon
            disp('Ratkaisu löydettiin halutulla tarkkuudella.')
            return
        end
        if r < 0
            z1 = z;
        else
            z2 = z;
        end 
    end
    disp('Iteraatiokertojen maksimi ylittyi eikä haluttua tarkkuutta saavutettu.')
    return
end