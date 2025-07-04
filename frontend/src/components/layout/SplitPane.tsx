import React, { useRef, useState } from 'react';

interface SplitPaneProps {
  left: React.ReactNode;
  right: React.ReactNode;
  initialSplit?: number; // percentage width for left pane
}

const SplitPane: React.FC<SplitPaneProps> = ({
  left,
  right,
  initialSplit = 50,
}) => {
  const [split, setSplit] = useState(initialSplit);
  const containerRef = useRef<HTMLDivElement>(null);
  const startData = useRef<{ startX: number; startLeft: number } | null>(null);

  const onMouseDown = (e: React.MouseEvent<HTMLDivElement>) => {
    e.preventDefault();
    const containerWidth = containerRef.current?.getBoundingClientRect().width ?? 0;
    const leftWidth = (split / 100) * containerWidth;
    startData.current = { startX: e.clientX, startLeft: leftWidth };

    const onMouseMove = (ev: MouseEvent) => {
      if (!startData.current) return;
      const deltaX = ev.clientX - startData.current.startX;
      const newLeft = Math.min(
        containerWidth * 0.9,
        Math.max(containerWidth * 0.1, startData.current.startLeft + deltaX)
      );
      setSplit((newLeft / containerWidth) * 100);
    };

    const onMouseUp = () => {
      window.removeEventListener('mousemove', onMouseMove);
      window.removeEventListener('mouseup', onMouseUp);
    };

    window.addEventListener('mousemove', onMouseMove);
    window.addEventListener('mouseup', onMouseUp);
  };

  return (
    <div ref={containerRef} className="flex flex-row flex-1 border border-neutral-800 rounded">
      <div
        className="flex flex-col"
        style={{ flex: `0 0 ${split}%`, minWidth: 0 }}
      >
        {left}
      </div>
      <div
        className="w-1 bg-neutral-700 cursor-col-resize select-none"
        onMouseDown={onMouseDown}
      />
      <div
        className="flex flex-col"
        style={{ flex: `0 0 ${100 - split}%`, minWidth: 0 }}
      >
        {right}
      </div>
    </div>
  );
};

export default SplitPane; 