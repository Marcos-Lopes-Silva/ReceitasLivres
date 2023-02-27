

interface IProps {
    children: React.ReactNode
}

export default function Container(props: IProps) {
  return (
    <div>
      {props.children}
    </div>
  );
}