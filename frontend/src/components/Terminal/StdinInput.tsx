interface StdinInputProps {
  value: string;
  onChange: (val: string) => void;
}

const StdinInput: React.FC<StdinInputProps> = ({ value, onChange }) => {
  return (
    <textarea
      placeholder="Program input (stdin)"
      className="bg-neutral-800 border-t border-neutral-700 p-2 resize-y text-xs text-foreground outline-none w-full"
      rows={3}
      value={value}
      onChange={(e) => onChange(e.target.value)}
    />
  );
};

export default StdinInput; 