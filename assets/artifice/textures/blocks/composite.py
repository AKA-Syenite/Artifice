import os
from PIL import Image

framebase = os.path.join(os.getcwd(), 'frame')

for cts in ['blastwall', 'glasswall', 'scaffold']:
    for root, dirs, files in os.walk(os.path.join(os.getcwd(), cts)):
        for base in files:
            if base.endswith('.png') and not (base.startswith('blast') or base.startswith('glass') or base.startswith('scaffold') or ('side' in base)):
                background = Image.open(os.path.join(root, base))
                for overlay in os.listdir(os.path.join(framebase, base[:-4])):
                    if overlay.endswith('.png'):
                        foreground = Image.open(os.path.join(os.path.join(framebase, base[:-4]), overlay))
                        final = Image.composite(foreground, background, foreground)
                        final.save(os.path.join(os.path.join(root, base)[:-4], cts + '_' + overlay[6:]))
