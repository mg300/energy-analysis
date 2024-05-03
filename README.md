# energy-analysis

### Przebieg działania programu
1. Odczyt danych z arkuszy kalkulacyjnych
2. Dopasowanie danych rzeczywistych do danych wzorcowych. W przypadku większej ilości danych rzeczywistych odpowiadających wartościom wzorcowym wyciągana jest średnia.
3. Generowanie raportu: Raport.xlsx
4. Konwersja typu do: Raport.ods

### Przebieg działania programu z interpolacją
Program zwiększa dokładność wykresu poprzez zmiane dokładności wzorca.

1. Odczyt danych z arkuszy kalkulacyjnych
2. Interpolacja danych wzorcowych do dokładności 0.01
3. Dopasowanie danych rzeczywistych do danych wzorcowych. W przypadku większej ilości danych rzeczywistych odpowiadających wartościom wzorcowym wyciągana jest średnia.
4. Generowanie raportu: Raport2.xlsx
5. Konwersja typu do: Raport2.ods

### Dependencies

- jopendocument
- apache poi
- apache commons
