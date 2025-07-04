const TerminalPlaceholder: React.FC = () => {
  return (
    <div className="flex flex-col h-full">
      <div className="bg-neutral-800 border-b border-neutral-700 px-3 py-1 text-xs font-semibold">
        Terminal (coming soon)
      </div>
      <pre className="flex-1 bg-neutral-900 text-xs p-3 overflow-y-auto whitespace-pre-wrap">
Compiling...\nProgram output will appear here.
      </pre>
      <textarea
        placeholder="Program input (stdin)"
        className="bg-neutral-800 border-t border-neutral-700 p-2 resize-y text-xs text-foreground"
        readOnly
      />
    </div>
  );
};

export default TerminalPlaceholder; 