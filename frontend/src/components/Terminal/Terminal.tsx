import React from 'react';

interface StreamItem {
  type: 'stdout' | 'stderr';
  text: string;
}

interface TerminalProps {
  streams: StreamItem[];
  className?: string;
}

const colorMap: Record<StreamItem['type'], string> = {
  stdout: 'text-green-400',
  stderr: 'text-red-400',
};

const Terminal: React.FC<TerminalProps> = ({ streams, className }) => {
  return (
    <pre
      className={`flex-1 bg-neutral-900 text-xs p-3 overflow-y-auto whitespace-pre-wrap ${className ?? ''}`}
    >
      {streams.map((s, idx) => (
        <span key={idx} className={colorMap[s.type]}>
          {s.text}
        </span>
      ))}
    </pre>
  );
};

export type { StreamItem };
export default Terminal; 