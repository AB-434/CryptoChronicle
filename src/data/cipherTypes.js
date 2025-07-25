export const cipherTypes = [
  {
    value: 'caesar',
    label: 'Caesar Cipher',
    keyPlaceholder: 'Enter shift value (e.g., 3)',
    messagePlaceholder: 'HELLO WORLD'
  },
  {
    value: 'monoalphabetic',
    label: "Monoalphabetic Cipher",
    keyPlaceholder: 'Enter a 26 character long key (e.g., QWERTYUIOPASDFGHJKLZXCVBNM)',
    messagePlaceholder: 'HELLO WORLD'
  },
  {
    value: 'playfair',
    label: 'Playfair Cipher',
    keyPlaceholder: 'Enter keyword (e.g., MONARCHY)',
    messagePlaceholder: 'INSTRUMENTS'
  },
  {
    value: 'vigenere',
    label: 'Vigenère Cipher',
    keyPlaceholder: 'Enter keyword (e.g., KEYWORD)',
    messagePlaceholder: 'ATTACKATDAWN'
  },
  {
    value: 'vernam',
    label: 'Vernam Cipher',
    keyPlaceholder: 'Enter keyword (e.g., KEYWORD)',
    messagePlaceholder: 'ATTACKATDAWN'
  },
  {
    value: 'hill',
    label: 'Hill Cipher',
    keyPlaceholder: 'Enter a string of 4 or 9 letters',
    messagePlaceholder: 'HELP'
  },
  {
    value: 'railfence',
    label: 'Rail Fence Cipher',
    keyPlaceholder: 'Enter number of rails (e.g., 3)',
    messagePlaceholder: 'WE ARE DISCOVERED FLEE AT ONCE'
  },
  {
    value: 'columnar',
    label: 'Columnar Transposition Cipher',
    keyPlaceholder: 'Enter the key (e,g., 43125)',
    messagePlaceholder: 'HELLO'
  },
  {
    value: 'otd',
    label: 'OTD',
    keyPlaceholder: 'Enter the key having length equal to the length of plain text',
    messagePlaceholder: 'HELLO'
  }
];