package domain.kernel

import domain.model.Output

trait Serializer[A]{
  def serialize(data: Output): Unit
}
