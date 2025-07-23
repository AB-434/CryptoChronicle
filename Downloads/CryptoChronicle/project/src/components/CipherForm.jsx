import React, { useState, useEffect } from 'react';
import { ChevronDown, RotateCcw, Loader2, Lock, Unlock } from 'lucide-react';
import { useDebounce } from '../hooks/useDebounce';
import { cipherTypes } from '../data/cipherTypes';

export const CipherForm = () => {
  const [message, setMessage] = useState('');
  const [key, setKey] = useState('');
  const [selectedCipher, setSelectedCipher] = useState('caesar');
  const [isEncrypt, setIsEncrypt] = useState(false);
  const [output, setOutput] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState('');

  // Debounce the inputs to avoid excessive API calls
  const debouncedMessage = useDebounce(message, 300);
  const debouncedKey = useDebounce(key, 300);

  const currentCipher = cipherTypes.find(cipher => cipher.value === selectedCipher);

  const processCipher = async (request) => {
    if (!request.message.trim()) {
      setOutput('');
      return;
    }

    setIsLoading(true);
    setError('');

    try {
      const response = await fetch('http://localhost:8080/api/cipher', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(request),
      });

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      const result = await response.text();
      setOutput(result);
    } catch (err) {
      setError(err instanceof Error ? err.message : 'An error occurred');
      setOutput('');
    } finally {
      setIsLoading(false);
    }
  };

  // Auto-process when inputs change
  useEffect(() => {
    const request = {
      message: debouncedMessage,
      key: debouncedKey,
      type: selectedCipher,
      encrypt: isEncrypt
    };

    processCipher(request);
  }, [debouncedMessage, debouncedKey, selectedCipher, isEncrypt]);

  const handleReset = () => {
    setMessage('');
    setKey('');
    setSelectedCipher('caesar');
    setIsEncrypt(true);
    setOutput('');
    setError('');
  };

  return (
    <div className="min-h-screen bg-gradient-to-br from-slate-50 to-blue-50 p-4">
      <div className="max-w-7xl mx-auto h-screen flex flex-col">
        {/* Header */}
        <div className="text-center py-6">
          <h1 className="text-3xl font-bold text-slate-800 mb-1">CryptoChronicle</h1>
          <p className="text-slate-600 text-sm">Classical Encryption and Decryption Algorithms</p>
        </div>

        {/* Main Content - Single Row Layout */}
        <div className="flex-1 grid grid-cols-1 lg:grid-cols-3 gap-6 min-h-0">
          {/* Left Panel - Controls */}
          <div className="bg-white rounded-xl shadow-lg p-6 flex flex-col">
            <h2 className="text-lg font-semibold text-slate-800 mb-4">Configuration</h2>
            
            {/* Cipher Type Selection */}
            <div className="mb-4">
              <label className="block text-sm font-medium text-slate-700 mb-2">
                Algorithm
              </label>
              <div className="relative">
                <select
                  value={selectedCipher}
                  onChange={(e) => setSelectedCipher(e.target.value)}
                  className="w-full appearance-none bg-white border-2 border-slate-200 rounded-lg px-3 py-2 pr-8 text-slate-700 focus:border-blue-500 focus:ring-2 focus:ring-blue-100 transition-all text-sm"
                >
                  {cipherTypes.map((cipher) => (
                    <option key={cipher.value} value={cipher.value}>
                      {cipher.label}
                    </option>
                  ))}
                </select>
                <ChevronDown className="absolute right-2 top-1/2 transform -translate-y-1/2 text-slate-400 w-4 h-4 pointer-events-none" />
              </div>
            </div>

            {/* Encrypt/Decrypt Toggle */}
            <div className="mb-4">
              <label className="block text-sm font-medium text-slate-700 mb-2">
                Operation
              </label>
              <div className="flex bg-slate-100 rounded-lg p-1">
                <button
                  onClick={() => setIsEncrypt(true)}
                  className={`flex-1 flex items-center justify-center gap-2 py-2 px-3 rounded-md font-medium transition-all text-sm ${
                    isEncrypt
                      ? 'bg-blue-500 text-white shadow-sm'
                      : 'text-slate-600 hover:text-slate-800'
                  }`}
                >
                  <Lock className="w-3 h-3" />
                  Encrypt
                </button>
                <button
                  onClick={() => setIsEncrypt(false)}
                  className={`flex-1 flex items-center justify-center gap-2 py-2 px-3 rounded-md font-medium transition-all text-sm ${
                    !isEncrypt
                      ? 'bg-blue-500 text-white shadow-sm'
                      : 'text-slate-600 hover:text-slate-800'
                  }`}
                >
                  <Unlock className="w-3 h-3" />
                  Decrypt
                </button>
              </div>
            </div>

            {/* Key Input */}
            <div className="mb-6">
              <label className="block text-sm font-medium text-slate-700 mb-2">
                Key
              </label>
              <input
                type="text"
                value={key}
                onChange={(e) => setKey(e.target.value)}
                placeholder={currentCipher?.keyPlaceholder || 'Enter key'}
                className="w-full border-2 border-slate-200 rounded-lg px-3 py-2 text-slate-700 focus:border-blue-500 focus:ring-2 focus:ring-blue-100 transition-all text-sm"
              />
            </div>

            {/* Reset Button */}
            <button
              onClick={handleReset}
              className="flex items-center justify-center gap-2 px-4 py-2 text-slate-600 hover:text-slate-800 hover:bg-slate-100 rounded-lg transition-all text-sm border border-slate-200"
            >
              <RotateCcw className="w-4 h-4" />
              Reset All
            </button>
          </div>

          {/* Middle Panel - Message Input */}
          <div className="bg-white rounded-xl shadow-lg p-6 flex flex-col">
            <div className="flex items-center justify-between mb-4">
              <h2 className="text-lg font-semibold text-slate-800">Message</h2>
              {isLoading && (
                <div className="flex items-center gap-2 text-blue-500">
                  <Loader2 className="w-4 h-4 animate-spin" />
                  <span className="text-xs">Processing...</span>
                </div>
              )}
            </div>
            
            <textarea
              value={message}
              onChange={(e) => setMessage(e.target.value)}
              placeholder={currentCipher?.messagePlaceholder || 'Enter your message'}
              className="flex-1 w-full border-2 border-slate-200 rounded-lg px-3 py-3 text-slate-700 focus:border-blue-500 focus:ring-2 focus:ring-blue-100 transition-all resize-none text-sm"
            />
          </div>

          {/* Right Panel - Output */}
          <div className="bg-white rounded-xl shadow-lg p-6 flex flex-col">
            <h2 className="text-lg font-semibold text-slate-800 mb-4">
              {isEncrypt ? 'Encrypted' : 'Decrypted'} Result
            </h2>

            <div className="flex-1 bg-slate-50 rounded-lg p-4 min-h-0 overflow-auto">
              {error ? (
                <div className="text-red-500 font-medium text-sm">
                  Error: {error}
                </div>
              ) : output ? (
                <div className="text-slate-800 font-mono text-sm break-all leading-relaxed">
                  {output}
                </div>
              ) : !message.trim() ? (
                <div className="text-slate-400 italic text-sm">
                  Enter a message to see the {isEncrypt ? 'decrypted' : 'encrypted'} result
                </div>
              ) : isLoading ? (
                <div className="text-slate-400 italic text-sm">
                  Processing your request...
                </div>
              ) : (
                <div className="text-slate-400 italic text-sm">
                  No result yet
                </div>
              )}
            </div>

            {output && (
              <div className="mt-3 pt-3 border-t border-slate-200 text-xs text-slate-500">
                <div className="flex flex-wrap gap-2">
                  <span className="bg-slate-100 px-2 py-1 rounded">
                    {currentCipher?.label}
                  </span>
                  <span className="bg-slate-100 px-2 py-1 rounded">
                    {isEncrypt ? 'Encrypt' : 'Decrypt'}
                  </span>
                  {key && (
                    <span className="bg-slate-100 px-2 py-1 rounded">
                      Key: {key}
                    </span>
                  )}
                </div>
              </div>
            )}
          </div>
        </div>

        {/* Footer */}
        <div className="text-center py-4 text-slate-500 text-xs">
          Supports Historical Cipher Algorithms with real-time processing
        </div>
      </div>
    </div>
  );
};