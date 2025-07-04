import React, { useRef, useState, useEffect } from 'react';
import TopBar from '@/components/TopBar';
import SplitPane from '@/components/layout/SplitPane';
import Editor from '@/components/Editor/Editor';
import TerminalPanel from '@/components/Terminal/TerminalPanel';
import { runCode } from '@/api/run';
import type { StreamItem } from '@/components/Terminal/Terminal';

const IdePage: React.FC = () => {
  const containerRef = useRef<HTMLDivElement>(null);
  const [code, setCode] = useState<string>(`// Write your Java code here\npublic class Main {\n  public static void main(String[] args) {\n    System.out.println(\"Hello, World!\");\n  }\n}`);
  const [stdin, setStdin] = useState('');
  const [streams, setStreams] = useState<StreamItem[]>([]);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    const saved = localStorage.getItem('lastCode');
    if (saved) setCode(saved);
  }, []);

  useEffect(() => {
    localStorage.setItem('lastCode', code);
  }, [code]);

  // Ctrl+Enter shortcut
  useEffect(() => {
    const handler = (e: KeyboardEvent) => {
      if (e.ctrlKey && e.key === 'Enter') {
        handleRun();
      }
    };
    window.addEventListener('keydown', handler);
    return () => window.removeEventListener('keydown', handler);
  }, [code, stdin]);

  const handleRun = async () => {
    setLoading(true);
    setStreams([]);
    try {
      const resp = await runCode({ code, stdin });
      if (resp.stdout) setStreams((s) => [...s, { type: 'stdout', text: resp.stdout }]);
      if (resp.stderr) setStreams((s) => [...s, { type: 'stderr', text: resp.stderr }]);
    } catch (err: any) {
      setStreams((s) => [...s, { type: 'stderr', text: `Error: ${err.message}\n` }]);
    } finally {
      setLoading(false);
    }
  };

  const handleClear = () => setStreams([]);

  return (
    <div className="flex flex-col h-full" ref={containerRef}>
      <TopBar onRun={handleRun} loading={loading} />
      <div className="flex flex-1 p-3">
        <SplitPane
          left={<Editor code={code} onChange={setCode} />}
          right={<TerminalPanel streams={streams} stdin={stdin} onStdinChange={setStdin} onClear={handleClear} />}
          initialSplit={60}
        />
      </div>
    </div>
  );
};

export default IdePage;
