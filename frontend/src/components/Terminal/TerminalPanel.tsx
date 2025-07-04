import React from 'react';
import Terminal, { StreamItem } from './Terminal';
import StdinInput from './StdinInput';
import ClearButton from '@/components/ClearButton';

interface TerminalPanelProps {
  streams: StreamItem[];
  stdin: string;
  onStdinChange: (v: string) => void;
  onClear: () => void;
}

const TerminalPanel: React.FC<TerminalPanelProps> = ({ streams, stdin, onStdinChange, onClear }) => {
  return (
    <div className="flex flex-col h-full">
      <div className="bg-neutral-800 border-b border-neutral-700 px-3 py-1 text-xs font-semibold flex items-center justify-between">
        <span>Terminal</span>
        <ClearButton onClick={onClear} />
      </div>
      <Terminal streams={streams} />
      <StdinInput value={stdin} onChange={onStdinChange} />
    </div>
  );
};

export default TerminalPanel; 